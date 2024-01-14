package com.goo99.goosreservation.data.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@ToString
public class PagingDto {

  private int pageNum;
  private int size;
  private int startPage;
  private int endPage;
  private int buttonLimit; // 프론트에서 보여지는 버튼 최대 개수
  private String directionColumn;

  public PagingDto() {
    this.pageNum = 1;
    this.size = 4;
    this.buttonLimit = 3;
    this.directionColumn = "reviewCount"; // 이용자수가 많은 순서로 정렬
  }

  public void setPagingDto(Pageable pageable, int totalPage) {
    int startPageTmp =
      (((int) Math.ceil(((double) pageable.getPageNumber() / this.buttonLimit))) - 1) * this.buttonLimit + 1;

    this.startPage = Math.max(1, startPageTmp);
    this.endPage =  Math.min((startPage + this.buttonLimit - 1), totalPage);
  }

}
