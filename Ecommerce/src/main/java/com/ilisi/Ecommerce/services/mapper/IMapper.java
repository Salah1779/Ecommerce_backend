package com.ilisi.Ecommerce.services.mapper;

public interface IMapper<BO, DTO> {
    /**
     * Converts a Business Object (BO) to a Data Transfer Object (DTO).
     * @param bo The business object to convert.
     * @return The corresponding DTO, or null if the input is null.
     */
    DTO toDTO(BO bo);

    /**
     * Converts a Data Transfer Object (DTO) to a Business Object (BO).
     * @param dto The DTO to convert.
     * @return The corresponding BO, or null if the input is null.
     */
    BO toBO(DTO dto);
}