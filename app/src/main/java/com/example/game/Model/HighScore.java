package com.example.game.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "highScores")
public class HighScore {
	
	@PrimaryKey(autoGenerate = true)
	private int id;
	
	@ColumnInfo(name = "score")
	private long score;
	
	@ColumnInfo(name = "country")
	private String country;
	
	@ColumnInfo(name = "owner")
	private String owner;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public long getScore() {
		return score;
	}
	
	public void setScore(long score) {
		this.score = score;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String getOwner() {
		return owner;
	}
	
	public void setOwner(String owner) {
		this.owner = owner;
	}
	
	public HighScore(long score, String country, String owner) {
		this.score = score;
		this.country = country;
		this.owner = owner;
	}
}
