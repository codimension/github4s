package github4s.domain

final case class SearchResultTextMatch(
    object_url: String,
    object_type: Option[String],
    property: String,
    fragment: String,
    matches: List[SearchResultTextMatchLocation]
)

final case class SearchResultTextMatchLocation(
    text: String,
    indices: List[Int]
)

sealed abstract class ComparisonOperator(val value: String)
case object LesserThan          extends ComparisonOperator("<=")
case object StrictlyLesserThan  extends ComparisonOperator("<")
case object GreaterThan         extends ComparisonOperator(">=")
case object StrictlyGreaterThan extends ComparisonOperator(">")
