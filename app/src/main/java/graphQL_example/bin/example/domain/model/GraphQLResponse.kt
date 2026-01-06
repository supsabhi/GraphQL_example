package graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLResponse<T>(
    val data: T? = null,
    val errors: List<GraphQLError>? = null
)