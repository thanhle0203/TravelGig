package com.thanhle.service;

import com.thanhle.domain.Document;

public interface DocumentService {
    Document saveDocument(Document document);

	public Document getDocumentById(Long id);
}
