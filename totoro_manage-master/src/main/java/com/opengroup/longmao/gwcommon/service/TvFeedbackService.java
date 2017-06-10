package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;
import com.opengroup.longmao.gwcommon.entity.po.TvFeedback;

/**
 * 【反馈意见表】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年04月25日 上午11:11:47
 */ 
public interface TvFeedbackService {

	 /**
	 * 【保存反馈意见表】
	 * @param tvFeedback
	 * @return tvFeedback
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:11:47
	 */ 
	 TvFeedback saveTvFeedback(TvFeedback tvFeedback);

	 /**
	 * 【删除反馈意见表】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:11:47
	 */ 
	 void deleteTvFeedback(Long id);


	 /**
	 * 【修改反馈意见表】
	 * @param tvFeedback
	 * @return tvFeedback
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:11:47
	 */ 
	 TvFeedback updateTvFeedback(TvFeedback tvFeedback);

	 /**
	 * 【查询反馈意见表】
	 * @param id
	 * @return TvFeedback
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:11:47
	 */ 
	 TvFeedback findTvFeedback(Long id);


	 /**
	 * 【查询反馈意见表】
	 * @param tvFeedback
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<TvFeedback>
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:11:47
	 */ 
	 Page<TvFeedback> findTvFeedback(TvFeedback tvFeedback,Integer pageNo,Integer pageSize,String sortField);


}

