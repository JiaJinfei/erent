package com.sxau.dao.impl;

import com.sxau.bean.Attachment;
import com.sxau.dao.IAttachmentDao;
import com.sxau.util.JDBCUtil;

public class AttachmentDao implements IAttachmentDao{

	JDBCUtil util = new JDBCUtil();
	
	@Override
	public int save(Attachment attachment) {
		String sql = "insert into attachment (id,name,path) values(seq_attachment.nextval,?,?)";
		String idSql = "select id from attachment where rowId = ?";
		return util.insert(sql, idSql, new Object[]{attachment.getName(),attachment.getPath()});
	}

	@Override
	public Attachment get(int id) {
		String sql = "select * from attachment where id = ?";
		return util.executeQuery(Attachment.class, sql, new Object[]{id}).get(0);
	}

}
