
// Dsp-block register_template
// Description here
// Inititally written by dsp-blocks initmodule.sh, 20210130
package register_template

import chisel3._
import chisel3.util._
import chisel3.experimental._
import chisel3.stage.{ChiselStage, ChiselGeneratorAnnotation}
import dsptools.{DspTester, DspTesterOptionsManager, DspTesterOptions}
import dsptools.numbers._

/*IO definitions for testmodule */
class register_template_io[T <:Data](proto: T,n: Int)
   extends Bundle {
        val A       = Input(Vec(n,proto))
        val B       = Output(Vec(n,proto))
   }

/** Module definition for testmodule
  * @param proto type information
  * @param n number of elements in register
  */
class register_template[T <:Data] (proto: T,n: Int) extends Module {
    val io = IO(new register_template_io( proto=proto, n=n))
    val register=RegInit(VecInit(Seq.fill(n)(0.U.asTypeOf(proto.cloneType))))
    register:=io.A
    io.B:=register
}

/** This gives you verilog */
object register_template extends App {
    val annos = Seq(ChiselGeneratorAnnotation(() => new register_template(
        proto=DspComplex(UInt(16.W),UInt(16.W)), n=8
    )))
    (new ChiselStage).execute(args, annos)
}

