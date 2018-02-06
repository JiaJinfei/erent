package com.sxau.service;

import com.sxau.bean.Attachment;

public interface IAttachmentService {
	
	public int save(Attachment attachment);
	
	public Attachment get(int id);
}
