package dto;

public class PitcherDto extends HumanDto{

	private String position;
	private int win;
	private int lose;
	private double defence;
	
	public PitcherDto() {
	}

	public PitcherDto(String position, int win, int lose, double defence) {
		super();
		this.position = position;
		this.win = win;
		this.lose = lose;
		this.defence = defence;
	}
	
	public PitcherDto(int number, String name, int age, double height, String position, int win, int lose, double defence) {
		super(number, name, age, height);
		this.position = position;
		this.win = win;
		this.lose = lose;
		this.defence = defence;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public double getDefence() {
		return defence;
	}

	public void setDefence(double defence) {
		this.defence = defence;
	}

	@Override
	public String toString() {		// 파일저장에 사용예정
		return super.toString() + "-" + position + "-" + win + "-" + lose + "-" + defence;
	}

	@Override
	public String info() {		
		return super.info() + " 포지션:" + position + " 승:" + win + " 패:" + lose + " 방어율:" + defence;
	}
	
}




