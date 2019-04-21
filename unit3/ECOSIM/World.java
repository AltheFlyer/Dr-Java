import java.util.ArrayList;

public class World {
    
    private Entity[][] map;

    private int numSheep;
    private int numWolves;
    private int numGrass;
    private double plantRate;
    private int width;
    private int height;
    
    //For special selections
    private int activeX;
    private int activeY;
    
    public World() {
        map = new Entity[5][5];
        /*
        map[0][0] = new Sheep(0, 0, this);
        map[0][1] = new Wolf(0, 1, this);
        
        map[2][0] = new Wolf(2, 0, this);
        map[2][1] = new Sheep(2, 1, this);
        
        map[0][4] = new Sheep(0, 4, this);
        map[1][4] = new Wolf(1, 4, this);
        
        map[4][4] = new Sheep(4, 4, this);
        map[3][4] = new Wolf(3, 4, this);
        
        */
        //map[3][3] = new Sheep(3, 3, this);
        //map[2][3] = new Grass(2, 3, this);
        //map[4][3] = new Grass(4, 3, this);
        //map[4][3].modHealth(20);
        
        
        plantRate = 0;
    }
    
    
    public World(int width, int height, double plantChance, double sheepChance, double wolfChance, 
                 double plantRate) {
        map = new Entity[height][width];
        this.width = width;
        this.height = height;
        this.plantRate = plantRate;
        
        activeX = 0;
        activeY = 0;
       
        numSheep = 0;
        numWolves = 0;
        numGrass = 0;
        
        System.out.println(plantChance);
        System.out.println(plantChance + sheepChance);
        System.out.println(plantChance + sheepChance + wolfChance);
    
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                double r = Math.random();
                //System.out.println(r);
                if (r <= plantChance) {
                    map[x][y] = new Grass(x, y, this);
                    numGrass++;
                } else if (r <= plantChance + sheepChance) {
                    map[x][y] = new Sheep(x, y, this);
                    numSheep++;
                } else if (r <= plantChance + sheepChance + wolfChance) {
                    map[x][y] = new Wolf(x, y, this);
                    numWolves++;
                } else {
                    map[x][y] = null;
                }
            }
        }
    }
    
    public void tick() { 
        numSheep = 0;
        numWolves = 0;
        numGrass = 0;
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                if (map[x][y] != null) {
                    if (!map[x][y].hasMoved()) {
                        map[x][y].exhaustMove();
                        int action = map[x][y].move();
                        int newX = x;
                        int newY = y;
                        if (action == 1) {
                            newY--;
                        } else if (action == 2) {
                            newX++;
                        } else if (action == 3) {
                            newY++;
                        } else if (action == 4) {
                            newX--;
                        }
                        
                        if (action > 0) {
                            if (hasEntity(newX, newY)) {
                                if (map[x][y].interact(map[newX][newY])) {
                                    map[newX][newY] = map[x][y];
                                    map[x][y] = null;
                                    map[newX][newY].setPos(newX, newY);
                                    if (x == activeX && y == activeY) {
                                        setActiveTile(newX, newY);
                                    }
                                }
                            } else if (tileExists(newX, newY)){
                                map[newX][newY] = map[x][y];
                                map[x][y] = null;
                                map[newX][newY].setPos(newX, newY);
                                if (x == activeX && y == activeY) {
                                    setActiveTile(newX, newY);
                                }
                            }
                            
                        }
                    }
                } else {
                    if (Math.random() < plantRate) {
                        map[x][y] = new Grass(x, y, this);
                    }
                }
            }
        }
        
        //Timer/deathchecks
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                if (map[x][y] != null){
                    map[x][y].tick();
                    if (map[x][y].getHealth() <= 0) {
                        map[x][y] = null;
                    } else {
                        if (map[x][y] instanceof Grass) {
                            numGrass++;
                        } else if (map[x][y] instanceof Sheep) {
                            numSheep++;
                        } else if (map[x][y] instanceof Wolf) {
                            numWolves++;
                        }
                    }   
                }
            }
        }
        
    }
    
    public String[][] getStringArray() {
        String[][] stringMap = new String[map[0].length][map.length];
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                if (map[x][y] != null) {
                    stringMap[x][y] = map[x][y].getEntityType();
                } else {
                    stringMap[x][y] = "0";
                }
            }
        }
        return stringMap;
    }
    
    /**
     * 
     * Bottom Inclusive
     */
    private int randint(int low, int high) {
        return (int) (low + Math.random() * (high - low));
    }
    
    public boolean tileExists(int x, int y) {
        return (x >= 0) && (x < map[0].length) && (y >= 0) && (y < map.length);
    }
    
    public boolean hasEntity(int x, int y) {
        return tileExists(x, y) && (map[x][y] != null);
    }
    
    public Entity getEntityAt(int x, int y) {
        return map[x][y];
    }
    
    public void addEntity(Entity e, int x, int y, int range) {
        int tilePosition = getClosestOpenTile(x, y, range);
        if (tilePosition > -1) {
            e.setPos(intToX(tilePosition), intToY(tilePosition));
            map[intToX(tilePosition)][intToY(tilePosition)] = e;
            
        }
    }
    
    /**
     * addEntityAroundTile
     * 
     */
    public int getClosestOpenTile(int x, int y, int range) {
        ArrayList<Integer> queue = new ArrayList<Integer>();
        int[][] visited = new int[map.length][map[0].length];
        
        queue.add(coordToInt(x, y));
        //visited[y][x] = 0;
        
        //System.out.println(range);
        
        while (!queue.isEmpty()) {
            //System.out.println(queue);
            int currentTile = queue.remove(0);
            int checkX = intToX(currentTile);
            int checkY = intToY(currentTile);
            
            //System.out.printf("%d\n", visited[checkY][checkX]);
            //System.out.println(visited[checkY][checkX] < range);
            //If Valid/Open square
            if (map[checkX][checkY] == null) {
                return coordToInt(checkX, checkY);
            }
            
            if (visited[checkX][checkY] < range) {
                //Add some tiles
                if (tileExists(checkX + 1, checkY) && visited[checkX + 1][checkY] == 0) {
                    queue.add(coordToInt(checkX + 1, checkY));
                    visited[checkX + 1][checkY] = visited[checkX][checkY] + 1;
                }
                //Add some tiles
                if (tileExists(checkX - 1, checkY) && visited[checkX - 1][checkY] == 0) {
                    queue.add(coordToInt(checkX - 1, checkY));
                    visited[checkX - 1][checkY] = visited[checkX][checkY] + 1;
                }
                //Add some tiles
                if (tileExists(checkX, checkY + 1) && visited[checkX][checkY + 1] == 0) {
                    queue.add(coordToInt(checkX, checkY + 1));
                    visited[checkX][checkY + 1] = visited[checkX][checkY] + 1;
                }
                //Add some tiles
                if (tileExists(checkX, checkY - 1) && visited[checkX][checkY - 1] == 0) {
                    queue.add(coordToInt(checkX, checkY - 1));
                    visited[checkX][checkY - 1] = visited[checkX][checkY] + 1;
                }                
            }

            visited[checkX][checkY] = -1;
        }
        
        return -1;
    }
    
    private int coordToInt(int x, int y) {
        return x + y * map.length;
    }
    
    private int intToX(int i) {
        return i % map.length;
    }
    
    private int intToY(int i) {
        return i / map.length;
    }
    
    public int getNumGrass() {
        return numGrass;
    }
    
    public int getNumSheep() {
        return numSheep;
    }
    
    public int getNumWolves() {
        return numWolves;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public String getEntityString(int x, int y) {
        if (map[x][y] == null) {
            return "";
        }
        return map[x][y].getEntityType();
    }
    
    public void setActiveTile(int x, int y) {
        activeX = x;
        activeY = y;
    }
    
    public int getActiveX() {
        return activeX;
    }
    
    public int getActiveY() {
        return activeY;
    }
}