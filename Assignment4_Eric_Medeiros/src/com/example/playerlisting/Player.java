package com.example.playerlisting;

/**
 * 
 * @author Eric Medeiros
 * A simple class to store the information about the player.
 *
 */
public class Player {
	private String name, position;
	private int goals;
	private long dbId;

	/**
	 * 
	 * @param name The name of the player.
	 * @param position The position that the player plays.
	 * @param goals the number of goals that the player has scored.
	 */
	public Player(String name, String position, int goals) {
		super();
		this.name = name;
		this.position = position;
		this.goals = goals;
	}

	/**
	 * Returns the players name.
	 * @return The players name as a string.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the players name.
	 * @param name The players name.
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Returns the players position.
	 * @return The players position as a string.
	 */
	public String getPosition() {
		return position;
	}

	/**
	 * Set the players position.
	 * @param position the players position.
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * Returns the number of goals that the player has scored.
	 * @return The number of goals as an int.
	 */
	public int getGoals() {
		return goals;
	}

	/**
	 * Set the number of goals that the player scored.
	 * @param goals The number of goals  scored.
	 */
	public void setGoals(int goals) {
		this.goals = goals;
	}
	
	/**
	 * Get the database ID of the player.
	 * @return The database ID as a long.
	 */
	public long getdbId(){
		return dbId;
	}
	
	/**
	 * Sets the database ID of the player.
	 * @param dbId The database ID of the player.
	 */
	public void setdbId(long dbId){
		this.dbId = dbId;
	}

}
