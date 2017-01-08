import Cafe._
import org.scalatest.{FlatSpec, Matchers}

class CafeTest extends FlatSpec with Matchers {

  "Bill" should "return the total bill of all the items passed to it" in {
    bill(List(Cola(), Coffee(), CheeseSandwich())) should be (3.5)
  }

  it should "return 0 for an empty list of items" in {
    bill(List.empty) should be (0)
  }

  "BillWithCharges" should "add any service charge if only drinks were purchased" in {
    billWithCharges(List(Cola(), Coffee())) should be (1.5)
  }

  it should "add 10% to the bill if any food was purchased" in {
    billWithCharges(List(Cola(), Coffee(), CheeseSandwich())) should be (3.85)
  }

  it should "add 20% to the bill if any hot food was purchased" in {
    billWithCharges(List(Cola(), Coffee(), SteakSandwich())) should be (7.2)
  }

  it should "limit the service charges to 20 if any hot food was purchased" in {
    billWithCharges(List(SteakSandwich(110))) should be (130)
  }

  "roundBill" should "round the bill to 2 decimals" in {
    roundBill(1.2222) should be (1.22)
  }
}