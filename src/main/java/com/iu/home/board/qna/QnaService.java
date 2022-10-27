package com.iu.home.board.qna;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.home.util.FileManager;
import com.iu.home.util.Pager;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional(rollbackFor = Exception.class) 		//exception발생시 롤백
public class QnaService {
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Value("${app.upload.qna}")
	private String path;	// /result/upload/qna
	
	@Autowired
	private FileManager fileManager;
	
	public QnaFileVO getDetailFile(QnaFileVO qnaFileVO)throws Exception{
		return qnaMapper.getDetailFile(qnaFileVO);
	}
	
	public int setDeleteFile(QnaFileVO qnaFileVO)throws Exception{
		qnaFileVO = qnaMapper.getDetailFile(qnaFileVO);
		int result = qnaMapper.setDeleteFile(qnaFileVO);
		
		if(result>0) {
			File file = new File(path, qnaFileVO.getFileName());
			file.delete();
		}
		
		return result;
	}
	
	
	public int setUpdate(QnaVO qnaVO)throws Exception{
		return qnaMapper.setUpdate(qnaVO);
	}
	
	public QnaVO getDetail(QnaVO qnaVO)throws Exception{
		return qnaMapper.getDetail(qnaVO);
	}
	
	
	public List<QnaVO> getList(Pager pager)throws Exception{
		return qnaMapper.getList(pager);
	}
	
	public int setAdd(QnaVO qnaVO)throws Exception{	
		int result = qnaMapper.setAdd(qnaVO);
		
		log.info("path : {}",path);
		File file = new File(path);
		
		if(!file.exists()) {
			boolean check = file.mkdirs();
		}

		
		for(MultipartFile f: qnaVO.getFiles()) {
//			if(f.isEmpty()) {
//				log.info("=================exception 발생===============");
//				throw new Exception();
//			}
			
			if(!f.isEmpty()) {
				log.info("fileName {}",f.getOriginalFilename());
				String fileName = fileManager.saveFile(f, path);
				QnaFileVO qnaFileVO = new QnaFileVO();
				qnaFileVO.setFileName(fileName);
				qnaFileVO.setOriName(f.getOriginalFilename());
				qnaFileVO.setNum(qnaVO.getNum());
				qnaMapper.setFileAdd(qnaFileVO);
			}
		}
		
		return result;
	}
	

}
