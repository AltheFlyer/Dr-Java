import java.util.ArrayList;

public class World {
    
    Entity[][] map;
    
    int sheepToSpawn = 0;
    
    public World() {
        map = new Entity[5][5];
        
        map[1][1] = new Grass(1, 1, this);
        System.out.println(getClosestOpenTile(1, 1, 1));
        map[1][0] = new Grass(1, 0, this);
        map[2][1] = new Grass(2, 1, this);
        map[0][1] = new Grass(0, 1, this);
        map[1][2] = new Grass(1, 2, this);
        System.out.println(getClosestOpenTile(1, 1, 1));
        System.out.println(getClosestOpenTile(1, 1, 2));
        
        map[3][3] = new Sheep(3, 3, this);
        map[4][3] = new Sheep(3, 4, this);
        
        /*
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                int r = randint(1, 5);
                if (r == 1) {
                    map[y][x] = new Grass(x, y, this);
                } else if (r == 2) {
                    map[y][x] = new Sheep(x, y, this);
                } else if (r == 3) {
                    map[y][x] = new Wolf(x, y, this);
                } else {
                    map[y][x] = null;
                }
            }
        }
        */
        
    }
    
    public void tick() { 
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                if (map[y][x] != null) {
                    if (!map[y][x].hasMoved()) {
                        map[y][x].exhaustMove();
                        int action = map[y][x].move();
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
                                if (map[y][x].interact(map[newY][newX])) {
                                    map[newY][newX] = map[y][x];
                                    map[y][x] = null;
                                    map[newY][newX].setPos(newX, newY);
                                }
                            } else if (tileExists(newX, newY)){
                                map[newY][newX] = map[y][x];
                                map[y][x] = null;
                                map[newY][newX].setPos(newX, newY);
                            }
                            
                        }
                    }
                } else {
                    if (randint(1, 11) == 10) {
                        map[y][x] = new Grass(x, y, this);
                    }
                }
            }
        }
        
        //Timer/deathchecks
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                if (map[y][x] != null){
                    if (map[y][x].getHealth() <= 0) {
                        map[y][x] = null;
                    } else {
                        map[y][x].tick();
                    }   
                }
            }
        }
        
    }
    
    public String[][] getStringArray() {
        String[][] stringMap = new String[map[0].length][map.length];
        for (int y = 0; y < map.length; ++y) {
            for (int x = 0; x < map[0].length; ++x) {
                if (map[y][x] != null) {
                    stringMap[y][x] = map[y][x].getEntityType();
                } else {
                    stringMap[y][x] = "0";
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
        return tileExists(x, y) && (map[y][x] != null);
    }
    
    public Entity getEntityAt(int x, int y) {
        return map[y][x];
    }
    
    public void addSheepTick() {
        sheepToSpawn++;
    }
    
    public void addEntity(Entity e, int x, int y, int range) {
        int tilePosition = getClosestOpenTile(x, y, range);
        if (tilePosition > -1) {
            e.setPos(intToX(tilePosition), intToY(tilePosition));
            map[intToY(tilePosition)][intToX(tilePosition)] = e;
            
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
            
            if (visited[checkY][checkX] < range) {
                //Add some tiles
                if (tileExists(checkX + 1, checkY) && visited[checkY][checkX + 1] == 0) {
                    queue.add(coordToInt(checkX + 1, checkY));
                    visited[checkY][checkX + 1] = visited[checkY][checkX] + 1;
                }
                //Add some tiles
                if (tileExists(checkX - 1, checkY) && visited[checkY][checkX - 1] == 0) {
                    queue.add(coordToInt(checkX - 1, checkY));
                    visited[checkY][checkX - 1] = visited[checkY][checkX] + 1;
                }
                //Add some tiles
                if (tileExists(checkX, checkY + 1) && visited[checkY + 1][checkX] == 0) {
                    queue.add(coordToInt(checkX, checkY + 1));
                    visited[checkY + 1][checkX] = visited[checkY][checkX] + 1;
                }
                //Add some tiles
                if (tileExists(checkX, checkY - 1) && visited[checkY - 1][checkX] == 0) {
                    queue.add(coordToInt(checkX, checkY - 1));
                    visited[checkY - 1][checkX] = visited[checkY][checkX] + 1;
                }                
            }
            
            //If Valid/Open square
            if (map[checkY][checkX] == null) {
                return coordToInt(checkX, checkY);
            }
            
            visited[checkY][checkX] = -1;
        }
        
        return -1;
    }
    
    private int coordToInt(int x, int y) {
        return x + y * map[0].length;
    }
    
    private int intToX(int i) {
        return i % map[0].length;
    }
    
    private int intToY(int i) {
        return i / map[0].length;
    }
}