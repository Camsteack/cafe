import Cafe._
import org.scalatest.{FlatSpec, Matchers}

class CafeTest extends FlatSpec with Matchers {

  "Bill" should "return the total bill of all the items passed to it" in {
    bill(List(Cola(), Coffee(), CheeseSandwich())) should be (3.5)
  }

  it should "return 0 for an empty list of items" in {
    bill(List.empty) should be (0)
  }
}