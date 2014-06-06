package com.w951.zsbus.main.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 系统版本：v1.0<br>
 * 开发人员：Lusifer<br>
 * 日期：2014-05-14<br>
 * 时间：20:37:00<br>
 * 功能描述：写明作用，调用方式，使用场景，以及特殊情况<br>
 *
 */
public class CategoryDTO implements Serializable {
	private static final long serialVersionUID = -1L;

    private String categoryId;
    private String categoryPid;
    private String categoryName;
    private String categoryNameUTF8;
    private Integer categorySort;
    private String categoryCreatename;
    private String categoryCreatedate;
    private List<CategoryDTO> children;
    private List<ArticleDTO> articles;

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }
    
    public String getCategoryPid() {
        return categoryPid;
    }

    public void setCategoryPid(String categoryPid) {
        this.categoryPid = categoryPid;
    }
    
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    
    public Integer getCategorySort() {
        return categorySort;
    }

    public void setCategorySort(Integer categorySort) {
        this.categorySort = categorySort;
    }
    
    public String getCategoryCreatename() {
        return categoryCreatename;
    }

    public void setCategoryCreatename(String categoryCreatename) {
        this.categoryCreatename = categoryCreatename;
    }
    
    public String getCategoryCreatedate() {
        return categoryCreatedate;
    }

    public void setCategoryCreatedate(String categoryCreatedate) {
        this.categoryCreatedate = categoryCreatedate;
    }

	public List<CategoryDTO> getChildren() {
		return children;
	}

	public void setChildren(List<CategoryDTO> children) {
		this.children = children;
	}

	public List<ArticleDTO> getArticles() {
		return articles;
	}

	public void setArticles(List<ArticleDTO> articles) {
		this.articles = articles;
	}

	public String getCategoryNameUTF8() {
		return categoryNameUTF8;
	}

	public void setCategoryNameUTF8(String categoryNameUTF8) {
		this.categoryNameUTF8 = categoryNameUTF8;
	}

}