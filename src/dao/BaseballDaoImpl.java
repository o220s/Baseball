package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dto.BatterDto;
import dto.HumanDto;
import dto.PitcherDto;
import file.DataProc;

public class BaseballDaoImpl implements BaseballDao{
	
	Scanner sc = new Scanner(System.in);
		
	private ArrayList<HumanDto> list;

	
	DataProc dataProc;
	
	public BaseballDaoImpl() {
		list = new ArrayList<>();
		
		
		dataProc = new DataProc("baseball");
		dataProc.createFile();
	}	
	
	@Override
	public void insert() {
		System.out.println("선수등록입니다");
		System.out.println("등록할 포지션을 입력해 주세요");
		System.out.print("투수(1)/타자(2) = ");
		int position = sc.nextInt();
		
		System.out.print("번호 = ");
		int number = sc.nextInt();
		
		System.out.print("이름 = ");
		String name = sc.next();
		
		System.out.print("나이 = ");
		int age = sc.nextInt();
		
		System.out.print("신장 = ");
		double height = sc.nextDouble();
		
		HumanDto humanDto = null;
		if(position == 1) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			humanDto = new PitcherDto(number, name, age, height, "투수", win, lose, defence);			
		}else {
			System.out.print("타수 = ");
			int batCount = sc.nextInt();
			
			System.out.print("히트수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			humanDto = new BatterDto(number, name, age, height, "타자", batCount, hit, hitAvg);			
		}		
		
		list.add(humanDto);
		
		System.out.println("등록되었습니다");
	}

	@Override
	public void delete() {		
		System.out.println("선수삭제입니다");
		System.out.print("삭제할 선수의 이름 = ");
		String name = sc.next();
		
		int index = search(name);		
		if(index == -1) {
			System.out.println("선수명단에 없습니다");
			return;
		}
		
		list.remove(index);
		
		System.out.println("삭제되었습니다"); 
	}

	@Override
	public void select() {
		
		System.out.println("선수검색입니다");
		System.out.print("검색할 선수의 이름 = ");
		String name = sc.next();
		
				// 1명이상일 경우 배열을 확보
				List<HumanDto> findList = new ArrayList<>();
				for (HumanDto h : list) {
					if (h != null && !h.getName().equals("")) {
						if (name.equals(h.getName())) {
							findList.add(h);
						}
					}
				}
				
				System.out.println("검색된 선수 명단입니다");
				for (HumanDto h : findList) {
					System.out.println(h.info());
				}
			}

	@Override
	public void update() {		
		System.out.println("선수수정입니다");
		System.out.print("수정할 선수의 이름 = ");
		String name = sc.next();
		
		int index = this.search(name);
		
		if(index == -1) {
			System.out.println("선수명단에 없습니다");
			return;
		}
		
		if(list.get(index) instanceof PitcherDto) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			PitcherDto p = (PitcherDto)list.get(index);
			p.setWin(win);
			p.setLose(lose);
			p.setDefence(defence);			
		}
		else {
			System.out.print("타수 = ");
			int batCount = sc.nextInt();
			
			System.out.print("안타수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			BatterDto b = (BatterDto)list.get(index);
			b.setBatCount(batCount);
			b.setHit(hit);
			b.setHitAvg(hitAvg);
		}		
		
		System.out.println("수정되었습니다");
	}

	@Override
	public void allPrint() {
		for (HumanDto h : list) {
				System.out.println(h.info());
			}
		}		
	
	

	@Override
	public void batSort() {
		
      List<HumanDto> humanBList = new ArrayList<>();
		
		// 타자만으로 (배열)구성
		for (HumanDto h : list) {
				if (h instanceof BatterDto) {
					humanBList.add(h);
			}
		}		
		
		
	
		// 순위(내림정렬)처리
		
		
		humanBList.sort((h1, h2) -> {
			if (h1 != null && !h1.getName().equals("") && h2 != null && !h2.getName().equals("")) {
				BatterDto b1 = (BatterDto) h1;
				BatterDto b2 = (BatterDto) h2;
				return Double.compare(b2.getHitAvg(), b1.getHitAvg());	// 비교는 타율로 한다
			}
			return 0;
		});
		
		for (int i = 0; i < humanBList.size(); i++) {		
			if (humanBList.get(i) != null) {
				System.out.println((i + 1) + "위"
						+ " 이름:" + humanBList.get(i).getName() 
						+ " 타율:" + ((BatterDto) humanBList.get(i)).getHitAvg());
			}
		}		
	}
		

	@Override
	public void pitchSort() {	
		
		 List<HumanDto> humanPList = new ArrayList<>();

		    // 투수만 선택 (리스트에서)
		    for (HumanDto h : list) {
		            if (h instanceof PitcherDto) {
		                humanPList.add(h);
		            }
		        }
		    

		    // 방어율을 기준으로 오름차순 정렬
		    humanPList.sort((h1, h2) -> {
		        if (h1 != null && !h1.getName().equals("") && h2 != null && !h2.getName().equals("")) {
		            PitcherDto p1 = (PitcherDto) h1;
		            PitcherDto p2 = (PitcherDto) h2;
		            return Double.compare(p1.getDefence(), p2.getDefence());
		        }
		        return 0;
		    });

		    System.out.println("투수 명단입니다 (방어율 순)");
		    for (int i = 0; i < humanPList.size(); i++) {
		        if (humanPList.get(i) != null) {
		            PitcherDto pitcher = (PitcherDto) humanPList.get(i);
		            System.out.println((i + 1) + "위"
		                    + " 이름:" + pitcher.getName()
		                    + " 방어율:" + pitcher.getDefence());
		        }
		    }
		}

	@Override
	public void save() {
		
		// 몇개의 데이터 수 파악
		int count = 0;
		for (HumanDto h : list) {
			if(h != null && !h.getName().equals("")) {
				count++;
			}
		}
		
		if(count == 0) {
			System.out.println("데이터가 없습니다");
			return;
		}
		
		// (string)배열설정
		String saveDatas[] = new String[count];
		
		// HumanDto -> String
		int c = 0;
		for (HumanDto h : list) {	
				saveDatas[c] = h.toString();
				c++;
			}
		
		
		// 저장
		dataProc.dataSave(saveDatas);
		
		System.out.println("저장되었습니다");
	}

	@Override
	public void load() {
		
		String datas[] = dataProc.dataLoad();		

		for (int i = 0; i < datas.length; i++) {
			String data[] = datas[i].split("-");
			
			if (data[4].equals("투수")) {
				list.add(new PitcherDto(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]),
						Double.parseDouble(data[3]), data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]),
						Double.parseDouble(data[7])));
			} else if (data[4].equals("타자")) {
				list.add(new BatterDto(Integer.parseInt(data[0]), data[1], Integer.parseInt(data[2]),
						Double.parseDouble(data[3]), data[4], Integer.parseInt(data[5]), Integer.parseInt(data[6]),
						Double.parseDouble(data[7])));
			}
		}
		
		System.out.println("데이터를 로드하였습니다");		
	}
	
	public int search(String name) {
		int index = -1;
		for (int i = 0; i < list.size(); i++) {
			HumanDto dto = list.get(i);
			if(name.equals(dto.getName())) {
				index = i;
				break;
			}
		}
		return index;
	}
	
}



