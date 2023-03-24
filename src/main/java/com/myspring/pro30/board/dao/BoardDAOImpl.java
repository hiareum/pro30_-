package com.myspring.pro30.board.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.myspring.pro30.board.vo.ArticleVO;
import com.myspring.pro30.member.vo.MemberVO;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO{
	
	//세션을 사용하므로 사용자의 정보를 저장함
	@Autowired
	private SqlSession sqlSession; 

	@Override
	public List selectAllArticlesList() throws DataAccessException {
		List<ArticleVO> articlesList = sqlSession.selectList("mapper.board.selectAllAreicleList");
		return articlesList;
	}

	@Override
	public int insertNewArticle(Map articleMap) throws DataAccessException {
		int articleNO = selectNewArticleNO();
		articleMap.put("articleNO", articleNO);
		sqlSession.insert("mapper.board.insertNewArticle",articleMap);
		return articleNO;
	}

	private int selectNewArticleNO() throws DataAccessException {
		return sqlSession.selectOne("mapper.board.selectNewArticleNO");
	}

	@Override
	public ArticleVO selectArticle(int articleNO) {
		return sqlSession.selectOne("mapper.board.selectArticle", articleNO);
	}
	//수정하기 했을때
	@Override
	public void updateArticle(Map articleMap) throws DataAccessException {
		sqlSession.update("mapper.board.updateArticle", articleMap);
	}
	
	//글 삭제
	@Override
	public void deleteArticle(int articleNO) throws DataAccessException {
		sqlSession.delete("mapper.board.deleteArticle", articleNO);
		
	}
}
