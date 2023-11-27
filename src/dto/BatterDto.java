package dto;

public class BatterDto extends HumanDto{
	
	private String position;
	private int batCount;
	private int hit;
	private double hitAvg;
	
	public BatterDto() {
	}

	public BatterDto(String position, int batCount, int hit, double hitAvg) {
		super();
		this.position = position;
		this.batCount = batCount;
		this.hit = hit;
		this.hitAvg = hitAvg;
	}
	
	public BatterDto(int number, String name, int age, double height, String position, int batCount, int hit, double hitAvg) {
		super(number, name, age, height);
		this.position = position;
		this.batCount = batCount;
		this.hit = hit;
		this.hitAvg = hitAvg;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getBatCount() {
		return batCount;
	}

	public void setBatCount(int batCount) {
		this.batCount = batCount;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public double getHitAvg() {
		return hitAvg;
	}

	public void setHitAvg(double hitAvg) {
		this.hitAvg = hitAvg;
	}

	@Override
	public String toString() {
		return super.toString() + "-" + position + "-" + batCount + "-" + hit + "-" + hitAvg;
	}

	@Override
	public String info() {		
		return super.info() + " 포지션:" + position + " 타수:" + batCount + " 안타수:" + hit + " 타율:" + hitAvg;
	}	
	
}





