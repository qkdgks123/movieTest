package com.my.movie.movie;

import java.util.List;

public interface MovieService {
	// CRUD ����� �޼ҵ� ����
	// ��ȭ ���
	 void insertMovie(MovieVO vo);

	// ��ȭ ����
	void updateMovie(MovieVO vo);

	// ��ȭ ����
	void deleteMovie(String m_code);

	// ��ȭ �� ��ȸ
	MovieVO getMovie(MovieVO vo);

	// ��ȭ ��� ��ȸ
	List<MovieVO> getMovieList(MovieVO vo);
	
	List<MovieVO> getMovieList(Criteria cri);
	
	int getTotal(Criteria cri);

	MovieVO getCode(String m_code);

	// �ش� m_code�� �´� movielist���
	MovieVO getMovieListFromM_code(String m_code);
}
