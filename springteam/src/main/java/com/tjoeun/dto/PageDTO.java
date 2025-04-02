package com.tjoeun.dto;

import lombok.Data;

@Data
public class PageDTO {
	
	// 최소 페이지 번호
	private int min;
	
	// 최대 페이지 번호
	private int max;
	
	// 이전 버튼의 페이지 번호
	private int previousPage;

	// 다음 버튼의 페이지 번호
	private int nextPage;
	
	// 전체 페이지 개수
	private int pageCount;
	
	// 현재 페이지 번호
	private int currentPage;
	
	// 생성자
	// contentCount			: 전체 게시글 개수
	// currentPage			: 현재 페이지 번호
	// page_listcount : 페이지당 게시글 개수
	// page_pagenationcount  : 페이지 버튼의 개수  - 화면 아랫쪽에 나오는 페이지 이동 버튼 개수
	public PageDTO(int contentCount, int currentPage, int page_listcount, int page_pagenationcount) {
		
		// 전체 페이지 개수 <-- 전체 게시글 개수 / 페이지 당 게시글 개수
		this.pageCount = contentCount / page_listcount;
		
		// 현재 페이지 번호 
		this.currentPage = currentPage;
		
		// contentCount / contentPageCount 결과가 0 으로 떨어지지 않는 경우 
		//                                 page 하나를 더 할당해야 됨
		if(contentCount % page_listcount > 0) pageCount++;
		
		/*	(currentPage - 1) 		 10 : 페이지 당 게시글 개수(contentPageCount)
		 	 현재 페이지 번호 - 1  / 10		   	    최소(min)		최대
		          0 ~ 9   / 10  :  0           	       1      10  <-- 최댓값은 최솟값에 9 를 더하면 됨
		         10 ~ 19  / 10  : 10           	  		11      20        ㄴ 최솟값만 구해서 9 를 더해서
		         20 ~ 29  / 10  : 20           	  		21      30           최댓값을 정함
		         30 ~ 39  / 10  : 30           				31      40
		         40 ~ 49  / 10  : 40           				41      50
		         50 ~ 59  / 10  : 50           				51      60
		               ...................
		 */
		this.min = ((currentPage - 1) / page_listcount) * page_listcount + 1;
		this.max = min + page_pagenationcount - 1;
		
		// 한 페이지 당 10 개의 게시글을 보여주는 경우,
		// 현재 page 에서 버튼의 최대 페이지 번호(max) 값이
		// 전체 페이지 개수(pageCount) 보다 커지면
		// 전체 페이지 개수(pageCount) 보다 큰 페이지 버튼까지 화면에 보여짐
		//																		ㄴ 이 부분은 필요없는 부분임 
		
		// 전체 페이지 개수(pageCount)가 72 인데
		// 최대 페이지 번호(max)가 73 이상이라면 (페이지 버튼은 71 ~ 79 가 나오게 됨)
		// 최대 페이지 번호(max) 값을 전체 페이지 개수(pageCount)로 해야
		// 필요없는 페이지 번호 버튼이 화면에 보이지 않게 됨
		
		if(this.max > pageCount) {
			this.max = pageCount;
		}
		
		// 이전 버튼이나 다음 버튼을 눌렀을 경우 
		previousPage = min - 1;
		nextPage = max + 1;
		
		if(nextPage > pageCount) {
			nextPage = pageCount;
		}
		
	}
	
	
}
