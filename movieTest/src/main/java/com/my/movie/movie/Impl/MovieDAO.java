package com.my.movie.movie.Impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.my.movie.movie.Criteria;
import com.my.movie.movie.MovieVO;


@Repository("MovieDAO")
public class MovieDAO {
	@Autowired
	private SqlSessionTemplate mybatis;

		// ��ȭ ���
		public void insertMovie(MovieVO vo) {
			  mybatis.insert("movie.insertMovie",vo);
		}

		// ��ȭ ����
		public void updateMovie(MovieVO vo) {
			System.out.println("===> JDBC�� updateBoard() ��� ó��");
			mybatis.update("movie.updateMovie",vo);
		}

		// ��ȭ ����
		public void deleteMovie(String m_code) {
			mybatis.delete("movie.deleteMovie", m_code);
		}

		// ��ȭ �� ��ȸ
		public MovieVO getMovie(MovieVO vo) {
			System.out.println("===> JDBC�� getMovie() ��� ó��");
			return mybatis.selectOne("movie.getMovie", vo);
		}
		// ��ȭ ����Ʈ ��ȸ
		public List<MovieVO> getMovieList(MovieVO vo) {
			return mybatis.selectList("movie.getMovieList",vo);
		}

		public MovieVO getCode(String m_code) {

			return mybatis.selectOne("movie.getCode", m_code);
		}
		
		//��ü �� ��ȸ
		public List<MovieVO> getMovieList(Criteria cri) {
			return mybatis.selectList("movie.getListWithPaging", cri);
		}
		//��ü �Ǽ� ��ȸ
		public int getTotalCount(Criteria cri) {
			return mybatis.selectOne("movie.getTotalCount", cri);
		}
		// �ش� m_code�� �´� movielist���
		public MovieVO getMovieListFromM_code(String m_code) {
			return mybatis.selectOne("movie.getMovieListFromMcode",m_code);
		}
}
