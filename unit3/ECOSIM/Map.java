public class Map {
    
    Entity[][] map;
    
    int sheepToSpawn = 0;
    
    public Map() {
        map = new Entity[3][3];
        
        
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
}