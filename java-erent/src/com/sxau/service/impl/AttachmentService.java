package com.sxau.service.impl;

import com.sxau.bean.Attachment;
import com.sxau.dao.IAttachmentDao;
import com.sxau.dao.impl.AttachmentDao;
import com.sxau.service.IAttachmentService;

public class AttachmentService implements IAttachmentService{

	IAttachmentDao attachmentDao = new AttachmentDao();
	
	@Override
	public int save(Attachment attachment) {
		return attachmentDao.save(attachment);
	}

	@Override
	public Attachment get(int id) {
		return attachmentDao.get(id);
	}

}
