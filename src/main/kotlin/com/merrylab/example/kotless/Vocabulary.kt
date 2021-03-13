package com.merrylab.example.kotless

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder
import com.amazonaws.services.dynamodbv2.model.AttributeValue
import com.amazonaws.services.dynamodbv2.model.GetItemRequest
import io.kotless.PermissionLevel
import io.kotless.dsl.lang.DynamoDBTable

private const val tableName: String = "vocabulary_en_ja"

@DynamoDBTable(tableName, PermissionLevel.Read)
object Vocabulary {

    private val client: AmazonDynamoDB = AmazonDynamoDBClientBuilder.standard().build()

    fun getByWord(word: String): String? {
        val req = GetItemRequest().withKey(mapOf(
            "en" to AttributeValue().apply { s = word }
        )).withTableName(tableName)

        val res = client.getItem(req).item

        return res?.let { it["ja"]?.s }
    }
}