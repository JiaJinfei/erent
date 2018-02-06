package com.sxau.dao;

import com.sxau.bean.Attachment;

public interface IAttachmentDao {

	public int save(Attachment attachment);
	
	public Attachment get(int id);
}
