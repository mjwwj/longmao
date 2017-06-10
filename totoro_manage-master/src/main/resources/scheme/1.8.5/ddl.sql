CREATE TABLE `live_h5` (
  `id` bigint(20) NOT NULL DEFAULT '0' COMMENT '活动id',
  `active_name` varchar(30) NOT NULL DEFAULT '' COMMENT '活动名称',
  `active_link` varchar(150) DEFAULT NULL COMMENT '活动地址',
  `active_ico` varchar(150) DEFAULT NULL COMMENT '活动图片地址',
  `directions` varchar(200) DEFAULT NULL COMMENT '活动描述',
  `soft_num` smallint(6) DEFAULT '0' COMMENT '排序',
  `up_time` int(11) DEFAULT NULL COMMENT '上架时间',
  `down_time` int(11) DEFAULT NULL COMMENT '下架时间',
  `is_enable` smallint(6) NOT NULL DEFAULT '2' COMMENT '是否展示(1-是 2-否)',
  `ctime` int(11) NOT NULL COMMENT '创建时间',
  `utime` int(11) NOT NULL COMMENT '修改时间',
  `is_delete` smallint(6) NOT NULL DEFAULT '1' COMMENT '是否删除(1-正常,-1-删除)',
  PRIMARY KEY (`id`,`active_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='直播间H5活动';

