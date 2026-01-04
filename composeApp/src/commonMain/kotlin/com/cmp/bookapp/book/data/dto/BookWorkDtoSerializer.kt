package com.cmp.bookapp.book.data.dto

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure

object BookWorkDtoSerializer: KSerializer<BookWorkDto> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor(
        BookWorkDto::class.simpleName!!
    ){
        element<String?>("description")
    }

    override fun serialize(
        encoder: Encoder,
        value: BookWorkDto
    ) {}

    override fun deserialize(decoder: Decoder): BookWorkDto = decoder.decodeStructure(descriptor) {
        val description: String? = null

        while (true){
            when(val index = decodeElementIndex())
        }
    }
}