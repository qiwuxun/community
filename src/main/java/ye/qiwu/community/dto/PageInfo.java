package ye.qiwu.community.dto;

import java.util.ArrayList;
import java.util.List;

public class PageInfo {
 private List<?> data;
 private boolean showPrevious;//是否有上一页
 private boolean showFirstPage;//是否显示首页
 private boolean showNext;//是否有下一页
 private boolean showEndPage;//是否显示最后一页
 private Integer page;//当前页码数
 private List<Integer> pages = new ArrayList<>();
 private Integer totalPage;//有多少页
 private Integer pageSize;//每一页显示的数量(条数)
private Integer total;//总记录数

 public void setPagination(Integer total, Integer page) {
  if (total % pageSize == 0) {
   this.totalPage = total / pageSize;
  } else {
   this.totalPage = total / pageSize + 1;
  }

  if (page < 1) {
   page = 1;
  }
  if (page > totalPage) {
   page = totalPage;
  }
  //  this.totalPage = totalPage;//19
  this.page = page;

  pages.add(page);
  //计算当前页码条里显示的页面数字
  for (int i = 1; i <= 3; i++) {
   if (page - i > 0) {
    pages.add(0, page - i);
   }

   if (page + i <= totalPage) {
    pages.add(page + i);
   }
  }

  // 是否展示上一页
  if (page == 1) {
   showPrevious = false;
  } else {
   showPrevious = true;
  }

  // 是否展示下一页
  if (page == totalPage) {
   showNext = false;
  } else {
   showNext = true;
  }

  // 是否展示第一页
  if (pages.contains(1)) {
   showFirstPage = false;
  } else {
   showFirstPage = true;
  }

  // 是否展示最后一页
  if (pages.contains(totalPage)) {
   showEndPage = false;
  } else {
   showEndPage = true;
  }
 }


 @Override
 public String toString() {
  return "PageInfo{" +
   "data=" + data +
   ", showPrevious=" + showPrevious +
   ", showFirstPage=" + showFirstPage +
   ", showNext=" + showNext +
   ", showEndPage=" + showEndPage +
   ", page=" + page +
   ", pages=" + pages +
   ", totalPage=" + totalPage +
   ", pageSize=" + pageSize +
   ", total=" + total +
   '}';
 }

 public Integer getPageSize() {
  return pageSize;
 }

 public void setPageSize(Integer pageSize) {
  this.pageSize = pageSize;
 }

 public List<?> getData() {
  return data;
 }

 public void setData(List<?> data) {
  this.data = data;
 }

 public boolean isShowPrevious() {
  return showPrevious;
 }

 public void setShowPrevious(boolean showPrevious) {
  this.showPrevious = showPrevious;
 }

 public boolean isShowFirstPage() {
  return showFirstPage;
 }

 public void setShowFirstPage(boolean showFirstPage) {
  this.showFirstPage = showFirstPage;
 }

 public boolean isShowNext() {
  return showNext;
 }

 public void setShowNext(boolean showNext) {
  this.showNext = showNext;
 }

 public boolean isShowEndPage() {
  return showEndPage;
 }

 public void setShowEndPage(boolean showEndPage) {
  this.showEndPage = showEndPage;
 }

 public Integer getPage() {
  return page;
 }

 public void setPage(Integer page) {
  this.page = page;
 }

 public List<Integer> getPages() {
  return pages;
 }

 public void setPages(List<Integer> pages) {
  this.pages = pages;
 }

 public Integer getTotalPage() {
  return totalPage;
 }

 public void setTotalPage(Integer totalPage) {
  if (this.total%this.pageSize==0){
   this.totalPage=this.total/this.pageSize;
  }else{
   this.totalPage=this.total/this.pageSize+1;
  }
 // this.totalPage = totalPage;
 }


 public Integer getTotal() {
  return total;
 }

 public void setTotal(Integer total) {
  this.total = total;
 }
}

