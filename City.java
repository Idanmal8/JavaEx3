
public class City {//fields
	private String name;
	private String country;
	private int numQuarantind;
	private int numSick;
	private int numHealty;
	private double coronaScore;
	private String color;


	public City(String name, String country, int numHealty , int numSick , int numQuarantind) {
		this.name = name;
		this.country = country;
		this.numHealty = numHealty;
		this.numSick = numSick;
		this.numQuarantind = numQuarantind;
		if(this.coronaScore > 0 && this.coronaScore < 0.3) {
			this.color = "Green";
		}else if(this.coronaScore >= 0.7) {
			this.color = "Red";
		}else if(this.coronaScore >= 0.3 && this.coronaScore < 0.7) {
			this.color ="Orange";
		}

	}
	//Copy constructor. 
	public City(City other) {
		this.name = other.name;
		this.country = other.country;
		this.numHealty = other.numHealty;
		this.numSick = other.numSick;
		this.numQuarantind = other.numQuarantind;
		this.coronaScore = other.coronaScore;
		this.color = other.color;

	}
	//this method comperss between citys.
	public City(City city1 , City city2) {
		country = city1.country;
		name = city1.name + "-" + city2.name ;
		numQuarantind = city1.numQuarantind + city2.numQuarantind;
		numSick = city1.numSick + city2.numSick;
		numHealty = city1.numHealty + city2.numHealty;
		coronaScore = (city1.numQuarantind + city1.numSick + city2.numSick + city2.numQuarantind)/(city1.numQuarantind + city1.numHealty + city1.numSick + city2.numSick + city2.numQuarantind+city2.numHealty);
		if(this.coronaScore >= 0 && this.coronaScore < 0.3) {
			this.color = "Green";
		}else if(this.coronaScore >= 0.7) {
			this.color = "Red";
		}else if(this.coronaScore >= 0.3 && this.coronaScore < 0.7) {
			this.color ="Orange";
		}
	}private void updateCoronaScore() {
		this.coronaScore=(double)(this.numSick + this.numQuarantind)/getResidents();
		if(this.coronaScore >= 0 && this.coronaScore < 0.3) {
			this.color = "Green";
		}else if(this.coronaScore >= 0.7) {
			this.color = "Red";
		}else if(this.coronaScore >= 0.3 && this.coronaScore < 0.7) {
			this.color ="Orange";
		}
	}//getters and setters
	public int sumOfPop() {
		return this.numHealty + this.numQuarantind + this.numSick;
	}
	public String getName () {
		return name; 
	}
	public String getCountry() {
		return country ;
	}
	public int getNumQuarantind() {
		return numQuarantind;
	}
	public int getNumSick() {
		return numSick;
	}
	public int getNumHealty() {
		return numHealty;
	}
	public double getCoronaScore() {
		return (this.numSick + this.numQuarantind)/(double)sumOfPop();
	}
	public String getColors() {
		if(getCoronaScore() >= 0 && getCoronaScore() < 0.3) {
			return "Green";
		}else if(getCoronaScore() >= 0.7) {
			return "Red";
		}else if(getCoronaScore() >= 0.3 && getCoronaScore() < 0.7) {
			return "Orange";
		}
		return color;
	}
	public int getResidents() {
		return this.numHealty + this.numQuarantind + this.numSick;
	}
	public double getPrecentHealth() {
		return ( this.numHealty/(double)(sumOfPop()))* 100;
	}//same countries and cities
	public boolean equals(City other) {
		if(other.name == this.name && other.country == this.country) {
			return true;
		}else {
			return false;
		}
	}//check if one city is safer then the other one by comparing the number of healthy people
	public boolean saferThan(City other) {
		if(this.numSick < other.numSick && this.numQuarantind < other.numQuarantind) {
			return true ; 
		}else {
			return false;
		}
	}//which city is bigger in terms of population
	public boolean biggerThan(City other) {
		if((this.numHealty + this.numQuarantind + this.numSick) > (other.numHealty + other.numQuarantind + other.numSick)) {
			return true;
		}else {
			return false;
		}
	}//this returns text of the information
	public String toString() {
		return "City:" + name + " country:" + country + " Has total: " + getResidents() +" residents ( Healty: " + numHealty + " Sick:" + numSick + 
				" quarantined: " + numQuarantind + ") %Health: " + String.format("%.2f", getPrecentHealth()) + "% Corona score: " + String.format("%.3f", getCoronaScore()) + "("+getColors()+")" ;
	}//sick %
	public double getSickRatio() {
		return (double)this.numSick/(this.numSick + this.numHealty + this.numQuarantind);
	}//quarantined %
	public double getQuarantinedRatio() {
		return (double)this.numQuarantind/(this.numQuarantind + this.numHealty + this.numSick);
	}//healthy %
	public double getHealthyRatio() {
		return (double)this.numHealty/(this.numHealty + this.numQuarantind + this.numSick);
	}// this adds new healthy residents to total
	public void addHealthyResidents(int numHealthy) {
		this.numHealty += numHealthy ;
	}// sends healthy people that got sick to quarantine.
	public void sendToQuarantine(int numNewQuarantine) {
		if(numNewQuarantine > this.numHealty) {
			numNewQuarantine = this.numHealty;
			this.numHealty = this.numHealty - numNewQuarantine  ;
			this.numQuarantind = this.numQuarantind + numNewQuarantine;
		}else if(numNewQuarantine < this.numHealty) {
			this.numHealty = this.numHealty - numNewQuarantine  ;
			this.numQuarantind = this.numQuarantind + numNewQuarantine;
			updateCoronaScore();

		}else {
			numHealty = this.numHealty;
		}
	}//releases from quarntine pepole who stop being sick
	public void releaseFromQuarantine(int numNewHealthy) {
		if(numNewHealthy >= this.numQuarantind) {
			numNewHealthy = this.numQuarantind;
			this.numHealty = numNewHealthy + this.numHealty;
		}else {
			this.numHealty = numNewHealthy + this.numHealty;
			this.numQuarantind = this.numQuarantind - numNewHealthy;
		}

	}//this multiplies the number of sick residents and take the amount needed of people from quarantined and healthy people and adds it to sick 
	public void multiplySickResidents(int factor) {
		if(factor > 0) {
			factor *= numSick;
			factor -= numSick;
			if(factor >= numHealty + numQuarantind) {
				numSick += numHealty + numQuarantind ;
				numHealty =0;
				numQuarantind = 0;
				updateCoronaScore();
			}else {
				if(numQuarantind >= factor) {
					numSick += factor;
					numQuarantind -= factor;
					updateCoronaScore();

				}else {
					numSick += numQuarantind;
					factor -= numQuarantind;
					numQuarantind = 0;
					numSick += factor;
					numHealty -= factor;
					updateCoronaScore();
				}
			}
		}



	}//releases people from the hospital(-numSick) and adds them to the numHealthy amount.
	public void releaseFromHospital(int numGotHealthy) {
		if(numGotHealthy >= this.numSick) {
			numGotHealthy = this.numSick;
			this.numHealty = this.numHealty + numGotHealthy;
			this.numSick = 0;
		}else {
			this.numHealty = this.numHealty + numGotHealthy;
			this.numSick = this.numSick - numGotHealthy;
		}
		updateCoronaScore();
	}

}
