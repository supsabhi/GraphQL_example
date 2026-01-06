package graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLRequest(
    val query: String,
    val variables: Map<String, String>? = null
)