package dao;

public interface BaseballDao {

	// 추가, 삭제, 검색, 수정, 타율순, 방어율순
	public void insert();
	public void delete();
	public void select();
	public void update();
	
	public void allPrint();
	
	public void batSort();
	public void pitchSort();
	
	public void save();
	public void load();	
	
}
