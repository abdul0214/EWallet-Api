package com.ewallet.api.service.exception.types;


import com.ewallet.api.model.BaseEntity;

public class ResourceNotFound extends RuntimeException {

	private long entityId;
	private String entityName;

	public ResourceNotFound() {
		super();
	}

	public ResourceNotFound(long entityId, String entityName) {
		super(String.format("Resource %s with id %s not found", entityName, entityId));
		this.entityId = entityId;
		this.entityName = entityName;
	}

	public ResourceNotFound(String msg, Class<? extends BaseEntity> type) {
		super(msg);
		this.entityName = type.getSimpleName();
	}

	public ResourceNotFound(long entityId, Class<? extends BaseEntity> type) {
		this(entityId, type.getSimpleName());
	}
}
