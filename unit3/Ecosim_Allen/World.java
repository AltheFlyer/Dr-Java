import java.util.ArrayList;

/**
 * [World.java]
 * @version 2.0
 * @author Allen Liu
 * @since April 26, 2019
 * A world object that can contain and control various entities
 */
public class World {
    
    //This is where all of the entities are stored
    private Entity[][] map;

    //Entity counters
    private int numSheep;
    private int numWolves;
    private int numGrass;
    //Plant growth rate
    private double plantRate;
    //Map dimensions
    private int width;
    private int height;
    
    /**
     * @param width the width of the world
     * @param height the height of the world
     * @param plantChance the chance for a tile to start with a plant
     * @param sheepChance the chance for a tile to start with a sheep 
     * @param wolfChance the chance for a tile to start with a wolf
     */
    public World(int width, int height, double plantChance, double sheepChance, double wolfChance, 
                 double plantRate) {
        map = new Entity[width][height];
        this.width = width;
        this.height = height;
        this.plantRate = plantRate;
       
        numSheep = 0;
        numWolves = 0;
        numGrass = 0;
    
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                //Determine what to spawn through rng
                double r = Math.random();

                if (r <= plantChance) {
                    map[x][y] = new Grass(x, y, this);
                    numGrass++;
                } else if (r <= (plantChance + sheepChance)) {
                    String gender = Genetics.getRandomGender();
                    map[x][y] = new Sheep(x, y, this, gender);
                    numSheep++;
                } else if (r <= (plantChance + sheepChance + wolfChance)) {
                    String gender = Genetics.getRandomGender();
                    map[x][y] = new Wolf(x, y, this, gender);
                    numWolves++;
                } else {
                    map[x][y] = null;
                }
            }
        }
    }
    
    /**
     * [tick]
     * runs world actions like entity movements, and other misc. operations
     * like entity counting and death checks
     */
    public void tick() { 
        //Reset entity counts
        numSheep = 0;
        numWolves = 0;
        numGrass = 0;
        //Do movements for every entity
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                if (map[x][y] != null) {
                    if (!map[x][y].hasMoved()) {
                        map[x][y].exhaustMove();
                        //Get a movement integer as an action
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
                                //Force interaction, do additional stuff if interaction allows for movement
                                if (map[x][y].interact(map[newX][newY])) {
                                    //Copy by movement, also deleting the entity on the tile to move to
                                    map[newX][newY] = map[x][y];
                                    //Remove old pointer by dereference
                                    map[x][y] = null;
                                    //Update position
                                    map[newX][newY].setPos(newX, newY);
                                }
                            } else if (tileExists(newX, newY)){
                                //Copy by movement
                                map[newX][newY] = map[x][y];
                                //Remove old pointer by dereference
                                map[x][y] = null;
                                //Update position
                                map[newX][newY].setPos(newX, newY);
                            }
                            
                        }
                    }
                //For empty tiles, try to randomly generate tall grass
                //The ground is naturally grassy, so this is for what will become TALL grass
                } else {
                    if (Math.random() < plantRate) {
                        map[x][y] = new Grass(x, y, this);
                    }
                }
            }
        }
        
        //Age timer/deathchecks/post-movement entity counter
        for (int x = 0; x < map.length; ++x) {
            for (int y = 0; y < map[0].length; ++y) {
                if (map[x][y] != null){
                    map[x][y].tick();
                    //The deathcheck
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
    
    /**
     * [tileExists]
     * checks if a tile with the given coordinates (x, y) exists in the map
     * @return boolean, whether the coordinates are for a valid tile or not
     */
    public boolean tileExists(int x, int y) {
        return (x >= 0) && (x < map[0].length) && (y >= 0) && (y < map.length);
    }
    
    /**
     * [hasEntity]
     * checks if the specified tile has an entity,
     * a more readable way of checking if != null
     * @return boolean, whether there is an entity at the specified tile or not
     */
    public boolean hasEntity(int x, int y) {
        return this.tileExists(x, y) && (this.map[x][y] != null);
    }
    
    /**
     * [getEntityAt]
     * gets the entity at the specified (x, y) coordinates
     * @param x the x coordinate of the tile to get the entity from
     * @param y the y coordinate of the tile to get the entity from
     * @return Entity, the entity at the (x, y) tile specified
     */
    public Entity getEntityAt(int x, int y) {
        return this.map[x][y];
    }
    
    /**
     * [addEntity]
     * adds an entity to a tile in the world, or the nearest unoccupied tile if there is an entity at that tile
     * @param e the entity to add to the world
     * @param x the x position of the tile to add the entity to/around
     * @param y the y position of the tile to add the entity to/around
     * @param range the maximum range to search for entity addition to around the (x, y) position
     */
    public void addEntity(Entity e, int x, int y, int range) {
        int tilePosition = getClosestOpenTile(x, y, range);
        if (tilePosition > -1) {
            e.setPos(this.intToX(tilePosition), this.intToY(tilePosition));
            this.map[this.intToX(tilePosition)][this.intToY(tilePosition)] = e;
            
        }
    }
    
    /**
     * [getClosestOpenTile]
     * given a tile, finds the nearest tile without an entity using bfs
     * @param x the x position of the tile to search from
     * @param y the y position of the tile to search from
     * @param range the maximum range to search
     * @return int, the integer representation of the nearest open tile. 
     * Returns -1 when no valid tiles are in range
     */
    public int getClosestOpenTile(int x, int y, int range) {
        ArrayList<Integer> queue = new ArrayList<Integer>();
        int[][] visited = new int[this.map.length][this.map[0].length];
        
        queue.add(this.coordToInt(x, y));
        //visited[y][x] = 0;
        
        //System.out.println(range);
        
        while (!queue.isEmpty()) {
            //System.out.println(queue);
            int currentTile = queue.remove(0);
            int checkX = this.intToX(currentTile);
            int checkY = this.intToY(currentTile);
            
            //System.out.printf("%d\n", visited[checkY][checkX]);
            //System.out.println(visited[checkY][checkX] < range);
            //If Valid/Open square
            if (this.map[checkX][checkY] == null) {
                return this.coordToInt(checkX, checkY);
            }

            if (visited[checkX][checkY] < range) {
                //Add some tiles
                if (tileExists(checkX + 1, checkY) && visited[checkX + 1][checkY] == 0) {
                    queue.add(this.coordToInt(checkX + 1, checkY));
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
    
    /**
     * [coordToInt]
     * converts a tile's (x, y) coordinate into an integer, for storage in data structures
     * map/world tiles are ordered in English reading order, left to right in each row, from top to bottom
     * @param x the x position of the tile
     * @param y the y position of the tile
     * @return int, the integer representation of the tile
     */
    public int coordToInt(int x, int y) {
        return x + y * this.map.length;
    }
    
    /**
     * [intToX]
     * converts an integer representation of a map tile into its respective x coordinate
     * @return int, the x coordinate of the specified tile
     */
    public int intToX(int i) {
        return i % this.map.length;
    }
    
    /**
     * [intToY]
     * converts an integer representation of a map tile into its respective y coordinate
     * @return int, the y coordinate of the specified tile
     */
    public int intToY(int i) {
        return i / this.map.length;
    }
    
    /**
     * [getNumGrass]
     * gets the number of tall grass tiles in the world
     * @return int numGrass, the number of tall grass tiles on the map
     */
    public int getNumGrass() {
        return this.numGrass;
    }
    
    /**
     * [getNumSheep]
     * gets the number of sheep in the world
     * @return int numSheep, the number of sheep on the map
     */
    public int getNumSheep() {
        return this.numSheep;
    }
    
    /**
     * [getNumWolves]
     * gets the number of wolves in the world
     * @return int numWolves, the number wolves of on the map
     */
    public int getNumWolves() {
        return this.numWolves;
    }
    
    /**
     * [getWidth]
     * gets the width of the world in tiles
     * @return int width, the width of the world in tiles
     */
    public int getWidth() {
        return this.width;
    }
    
    /**
     * [getHeight]
     * gets the height of the world in tiles
     * @return int height, the height of the world in tiles
     */
    public int getHeight() {
        return this.height;
    }
    
    /**
     * [getEntityType]
     * a shorthand to find the type of an entity at a given tile
     * @param x the x position of the tile to get the entity from
     * @param y the y position of the tile to get the entity from
     * @return String, the type string of the entity at a given tile.
     * Returns an empty string if null
     */
    public String getEntityType(int x, int y) {
        if (this.map[x][y] == null) {
            return "";
        }
        return this.map[x][y].getEntityType();
    }
}