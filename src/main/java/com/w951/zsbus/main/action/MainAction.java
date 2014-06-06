package com.w951.zsbus.main.action;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.w951.util.action.CommonBaseAction;
import com.w951.util.net.RequestHtml;
import com.w951.zsbus.main.dto.ArticleDTO;
import com.w951.zsbus.main.dto.CategoryDTO;

public class MainAction extends CommonBaseAction {
	private static final long serialVersionUID = 5557016719598518314L;
	private JSONObject result;
	private JSONArray resultArray;
	private Map<String, Object> request;
	private Map<String, Object> session;
	
	private static final String newsUrl = System.getProperty("newsUrl");
	
	// Parameter
	
	private int page;
	private int rows = 20;
	private String categoryId;
	private String categoryName;
	private String articleId;
	
	// Action
	
	public String index() throws Exception {
		navigate();
		
		// 主页显示
		String indexCategories = RequestHtml.postHtml(newsUrl + "news/api/Category/queryByShow");
		List<CategoryDTO> indexDtos = new ArrayList<CategoryDTO>();
		if (indexCategories != null && indexCategories.length() > 0) {
			JSONArray array = JSONArray.fromObject(indexCategories);
			for (int i = 0 ; i < array.size() ; i++) {
				JSONObject obj = array.getJSONObject(i);
				CategoryDTO dto = new CategoryDTO();
				dto = new CategoryDTO();
				dto.setCategoryCreatedate(obj.getString("categoryCreatedate"));
				dto.setCategoryId(obj.getString("categoryId"));
				dto.setCategoryName(obj.getString("categoryName"));
				dto.setCategoryPid(obj.getString("categoryPid"));
				dto.setCategorySort(obj.getInt("categorySort"));
				dto.setChildren(new ArrayList<CategoryDTO>());
				dto.setArticles(new ArrayList<ArticleDTO>());
				
				// 所属文章
				String pCategoryArticles = RequestHtml.postHtml(newsUrl + "news/api/Article/queryByCategory?page=1&rows=10&categoryId=" + dto.getCategoryId());
				JSONArray pArticles = JSONArray.fromObject(pCategoryArticles);
				for (int x = 0 ; x < pArticles.size() ; x++) {
					JSONObject artObj = pArticles.getJSONObject(x);
					ArticleDTO artDto = new ArticleDTO();
					artDto.setArticleAuthor(artObj.getString("articleAuthor"));
					artDto.setArticleContent(artObj.getString("articleContent"));
					artDto.setArticleCreatedate(artObj.getString("articleCreatedate"));
					artDto.setArticleId(artObj.getString("articleId"));
					artDto.setArticleSubtitle(artObj.getString("articleSubtitle"));
					artDto.setArticleTitle(artObj.getString("articleTitle"));
					artDto.setCategoryId(artObj.getString("categoryId"));
					artDto.setCategoryName(artObj.getString("categoryName"));
					dto.getArticles().add(artDto);
				}
				
				if (dto.getArticles().size() < 10) {
					final int articleSize = dto.getArticles().size();
					for (int x = 0 ; x < 10 - articleSize ; x++) {
						dto.getArticles().add(new ArticleDTO());
					}
				}
				
				indexDtos.add(dto);
			}
		}
		
		request.put("categories", indexDtos);
		
		return SUCCESS;
	}
	
	public String category() throws Exception {
		navigate();
		
		if (page == 0) {
			page = 1;
		}
		String result = RequestHtml.postHtml(String.format(newsUrl + "news/api/Article/queryByCategory?page=%s&rows=%s&categoryId=%s", page, rows, categoryId));
		JSONArray array = JSONArray.fromObject(result);
		List<ArticleDTO> dtos = new ArrayList<ArticleDTO>();
		for (int i = 0 ; i < array.size() ; i++) {
			JSONObject obj = array.getJSONObject(i);
			ArticleDTO dto = new ArticleDTO();
			dto.setArticleAuthor(obj.getString("articleAuthor"));
			dto.setArticleContent(obj.getString("articleContent"));
			dto.setArticleCreatedate(obj.getString("articleCreatedate"));
			dto.setArticleId(obj.getString("articleId"));
			dto.setArticleSubtitle(obj.getString("articleSubtitle"));
			dto.setArticleTitle(obj.getString("articleTitle"));
			dto.setCategoryId(obj.getString("categoryId"));
			dto.setCategoryName(obj.getString("categoryName"));
			dtos.add(dto);
		}
		
		long count = 0;
		String pageCountStr = RequestHtml.postHtml(String.format(newsUrl + "news/api/Article/queryCountByCategory?categoryId=%s", categoryId));
		JSONArray pageCountJson = JSONArray.fromObject(pageCountStr);
		count = pageCountJson.getLong(0);
		
		long pageCount = count / rows;
		if (count > rows && count % rows > 0) {
			pageCount += 1;
		}
		request.put("pageCount", pageCount);
		request.put("rows", rows);
		request.put("categoryId", categoryId);
		request.put("categoryName", URLDecoder.decode(categoryName, "UTF-8"));
		request.put("articles", dtos);
		
		return SUCCESS;
	}
	
	public String article() throws Exception {
		navigate();
		
		String result = RequestHtml.postHtml(String.format(newsUrl + "news/api/Article/queryById?articleId=%s", articleId));
		JSONArray array = JSONArray.fromObject(result);
		JSONObject obj = array.getJSONObject(0);
		ArticleDTO dto = new ArticleDTO();
		dto.setArticleAuthor(obj.getString("articleAuthor"));
		dto.setArticleContent(URLDecoder.decode(obj.getString("articleContent"), "UTF-8"));
		dto.setArticleCreatedate(obj.getString("articleCreatedate"));
		dto.setArticleId(obj.getString("articleId"));
		dto.setArticleSubtitle(obj.getString("articleSubtitle"));
		dto.setArticleTitle(obj.getString("articleTitle"));
		dto.setCategoryId(obj.getString("categoryId"));
		dto.setCategoryName(obj.getString("categoryName"));
		
		request.put("article", dto);
		
		return SUCCESS;
	}
	
	private void navigate() throws Exception {
		// 导航栏
		String result = RequestHtml.postHtml(newsUrl + "news/api/Category/query");
		List<CategoryDTO> dtos = new ArrayList<CategoryDTO>();
		if (result != null && result.length() > 0) {
			JSONArray array = JSONArray.fromObject(result);
			for (int i = 0; i < array.size(); i++) {
				JSONObject obj = array.getJSONObject(i);
				CategoryDTO dto = new CategoryDTO();
				dto = new CategoryDTO();
				dto.setCategoryCreatedate(obj.getString("categoryCreatedate"));
				dto.setCategoryId(obj.getString("categoryId"));
				dto.setCategoryName(obj.getString("categoryName"));
				dto.setCategoryNameUTF8(URLEncoder.encode(dto.getCategoryName(), "UTF-8"));
				dto.setCategoryPid(obj.getString("categoryPid"));
				dto.setCategorySort(obj.getInt("categorySort"));
				dto.setChildren(new ArrayList<CategoryDTO>());
				dto.setArticles(new ArrayList<ArticleDTO>());

				JSONArray children = JSONArray.fromObject(obj.get("children"));
				for (int x = 0; x < children.size(); x++) {
					JSONObject chiObj = children.getJSONObject(x);
					CategoryDTO chiDto = new CategoryDTO();
					chiDto = new CategoryDTO();
					chiDto.setCategoryCreatedate(chiObj.getString("categoryCreatedate"));
					chiDto.setCategoryId(chiObj.getString("categoryId"));
					chiDto.setCategoryName(chiObj.getString("categoryName"));
					chiDto.setCategoryNameUTF8(URLEncoder.encode(chiDto.getCategoryName(), "UTF-8"));
					chiDto.setCategoryPid(chiObj.getString("categoryPid"));
					chiDto.setCategorySort(chiObj.getInt("categorySort"));
					dto.getChildren().add(chiDto);
				}

				dtos.add(dto);
			}
		}
		request.put("menus", dtos);
	}
	
	public JSONObject getResult() {
		return result;
	}

	public void setResult(JSONObject result) {
		this.result = result;
	}

	public JSONArray getResultArray() {
		return resultArray;
	}

	public void setResultArray(JSONArray resultArray) {
		this.resultArray = resultArray;
	}

	public Map<String, Object> getRequest() {
		return request;
	}

	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}
}
