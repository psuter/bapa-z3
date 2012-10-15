package bapa

object Main {
  def main(args : Array[String]) {
    demo1()
    demo2()
  }

  def demo1() {
    // A very quick intro to using the plugin... 
    import z3.scala._

    val ctx  = new Z3Context()
    val bapa = new BAPATheory(ctx)

    val a = ctx.mkFreshConst("A", bapa.mkSetSort)
    val b = ctx.mkFreshConst("B", bapa.mkSetSort)
  
    // A != B
    ctx.assertCnstr(ctx.mkDistinct(a, b))
    // A subset of B
    ctx.assertCnstr(bapa.mkSubsetEq(a, b)) 

    // At this stage, the constraints are still satisfiable.
    println(ctx.check) // Some(true), meaning SAT

    // | A U B | > | B |
    val cardOfAUnionB = bapa.mkCard(bapa.mkUnion(a, b))
    ctx.assertCnstr(ctx.mkGT(cardOfAUnionB, bapa.mkCard(b)))

    // The last constraint contradicts the assumption that A is a subset of B.
    println(ctx.check) // Some(false), meaning UNSAT

    ctx.delete
  }

  def demo2() {
    import z3.scala._

    val ctx  = new Z3Context
    val bapa = new BAPATheory(ctx)

    val a = ctx.mkFreshConst("a", bapa.mkElemSort) // elem sort is always Int
    val b = ctx.mkFreshConst("b", bapa.mkElemSort) 

    val s0 = ctx.mkFreshConst("S0", bapa.mkSetSort)
    val s1 = ctx.mkFreshConst("S1", bapa.mkSetSort)
    val s2 = ctx.mkFreshConst("S2", bapa.mkSetSort)

    ctx.assertCnstr(ctx.mkEq(s0, bapa.mkSingleton(a)))          // S0 = { a }
    ctx.assertCnstr(ctx.mkEq(s1, bapa.mkSingleton(b)))          // S1 = { b }
    ctx.assertCnstr(ctx.mkDistinct(a, b))                       // a != b
    ctx.assertCnstr(bapa.mkSubsetEq(s1, s2))                    // S1 ⊆ S2
    ctx.assertCnstr(ctx.mkLT(bapa.mkCard(s2), bapa.mkCard(s0))) // |S2| < |S0|

    println(ctx.check) // Some(false), meaning UNSAT
  } 
}
