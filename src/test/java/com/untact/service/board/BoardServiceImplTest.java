package com.untact.service.board;

import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.untact.persistent.board.BoardRepository;
import com.untact.vo.PageVO;

@RunWith(MockitoJUnitRunner.class)
public class BoardServiceImplTest {
	@Mock
	private BoardRepository boardRepo;
	@InjectMocks
	private BoardServiceImpl boardService;
	
	@Test
	public void initTest() {
		
	}
	
	@Test
	public void getListWithPagingAndGroupNumberTest() {
		
	}
}
