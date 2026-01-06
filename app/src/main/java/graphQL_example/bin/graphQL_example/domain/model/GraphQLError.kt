package graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class GraphQLError(
    val message: String
)