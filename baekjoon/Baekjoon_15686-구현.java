package Algorithm.boj.day0721;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Baekjoon_15686 {
	// 필요 변수 선언
	static int N;
	static int M;
	static char[][] map;
//	ArrayList<int[]> chiPos;	// 치킨집 좌표
//	ArrayList<int[]> homePos;	// 집 좌표
//	ArrayList<Integer> chiDist;	// 각 치킨집의 집들과의 거리합 배열 -> 오름차순 정렬하여 M개만큼의 치킨집의 거리를 합산해 result에 저장
	static int result;		// 도시의 치킨 거리 최솟값
	
	
	
	// 아이디어 (실패한 코드 -> 오답) 
	// 각 치킨집에서 모든 집과의 거리를 구하고 그 총합(distSum)을 현재 치킨집 정보(i)와 함께 배열 chiDist에 저장
	// chiDist에 저장된 distSum을 기준으로 정렬, M까지만큼의 치킨집들(즉 집들과 가장 가까운 M개의 치킨집들)만 남기기
	// M까지만큼의 치킨집 정보만을 기준으로 '도시의 치킨 거리'를 계산하여 출력
	
	
	public static void main(String[] args) throws IOException {
		ArrayList<int[]> chiPos = new ArrayList<int[]>();
		ArrayList<int[]> homePos = new ArrayList<int[]>();
		Integer[][] chiDist;
		
		// 값 입력 받기
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				map[i][j] = st.nextToken().charAt(0);
			}
		}
	
	
		// 각 치킨집과 집의 좌표 배열 세팅
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				//  집 좌표 배열 추가
				if(map[i][j]=='1') {
					homePos.add(new int[] {i,j});
				}
				//  치킨집 좌표 배열 추가
				if(map[i][j]=='2') {
					chiPos.add(new int[] {i,j});
				}
			}
		}
		
		chiDist = new Integer[chiPos.size()][chiPos.size()];		
		
		// 각 치킨집에서 모든 집과의 거리를 구하고 그 총합(distSum)을 현재 치킨집 정보(i)와 함께 배열 chiDist에 저장
		for(int i=0; i<chiPos.size(); i++) {
			int y = chiPos.get(i)[0];
			int x = chiPos.get(i)[1];
			int distSum=0;
			
			// 거리 구하기
			for(int j=0; j<homePos.size(); j++) {
				distSum += (Math.abs(homePos.get(j)[0]-y) + Math.abs(homePos.get(j)[1]-x));
			}
			
			// chiDist에 저장
			chiDist[i] = new Integer[] {distSum, i};
		}
		
		// chiDist를 오름차순 정렬하고 M개까지 만큼의 요소까지 합산해 출력 

		Arrays.sort(chiDist, new Comparator<Integer[]>() {
            @Override
            public int compare(Integer[] o1, Integer[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });
		

//		for(int i=0; i<chiDist.length; i++) {
//			System.out.println(chiDist[i][0] + " " + chiDist[i][1]);
//		}

		
		// 선정한 치킨집만 남긴 상태에서 도시의 치킨 거리 계산
		int cityChickenDist=0;
		for(int[] h : homePos) {
			int hy = h[0];
			int hx = h[1];
			int distMin=Integer.MAX_VALUE;
			
			for(int i=0; i<M; i++) {
				int cy = chiPos.get(chiDist[i][1])[0];
				int cx = chiPos.get(chiDist[i][1])[1];
				distMin = Math.min( (Math.abs(hy-cy)+Math.abs(hx-cx)), distMin);
			}
			
			cityChickenDist+=distMin;
		}
		System.out.println(cityChickenDist);
	}
}
