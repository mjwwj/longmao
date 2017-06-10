package com.opengroup.longmao.gwcommon.service;

import org.springframework.data.domain.Page;
import com.opengroup.longmao.gwcommon.entity.po.TvIllegalReport;

/**
 * 【举报情况表】 service接口
 *
 * @version 1.0
 * @author Hermit 2017年04月25日 上午11:08:03
 */ 
public interface TvIllegalReportService {

	 /**
	 * 【保存举报情况表】
	 * @param tvIllegalReport
	 * @return tvIllegalReport
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */ 
	 TvIllegalReport saveTvIllegalReport(TvIllegalReport tvIllegalReport);

	 /**
	 * 【删除举报情况表】
	 * @param id
	 * @return void
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */ 
	 void deleteTvIllegalReport(Long id);


	 /**
	 * 【修改举报情况表】
	 * @param tvIllegalReport
	 * @return tvIllegalReport
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */ 
	 TvIllegalReport updateTvIllegalReport(TvIllegalReport tvIllegalReport);

	 /**
	 * 【查询举报情况表】
	 * @param id
	 * @return TvIllegalReport
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */ 
	 TvIllegalReport findTvIllegalReport(Long id);


	 /**
	 * 【查询举报情况表】
	 * @param tvIllegalReport
	 * @param pageNo
	 * @param pageSize
	 * @param sortField
	 * @return Page<TvIllegalReport>
	 * @version 1.0
	 * @author Hermit 2017年04月25日 上午11:08:03
	 */ 
	 Page<TvIllegalReport> findTvIllegalReport(TvIllegalReport tvIllegalReport,Integer pageNo,Integer pageSize,String sortField);


}

