package com.terranova.api.v1.product.domain.port.out;

import com.terranova.api.v1.product.infrastructure.adapter.in.web.dto.response.CloudinaryResponse;

public interface ImageStoragePort {

    CloudinaryResponse uploadToCloudinary(byte[] contentStream, String filename);

    void deleteImages(String fileName);
}
