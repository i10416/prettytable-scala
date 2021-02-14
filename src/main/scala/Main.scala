
import cats.effect.{IOApp,IO,ExitCode}
import cats.effect.ExitCase.Canceled
import commands._
import route.Route
import usecase.GenerateTableUsecase
import com.monovore.decline.{Command}

object Main extends IOApp {
  

  override def run(args:List[String]):IO[ExitCode] = {
    val command = Command(
        name="pretttable-scala",
        header="pritty print table in terminal",
        helpFlag = true
    )(Commands.appCmd)
    val usecase = GenerateTableUsecase()
    val route = Route(usecase)
    val program = command.parse(args.toSeq) match {
        case Right((globalFlags,cmd))=>
          dispatchCmd(globalFlags,cmd,route)
        case Left(e) =>
          IO(println(e.toString())) *> IO(ExitCode.Error)
    }
    program.guaranteeCase {
        case Canceled =>
          IO.unit
        case _ =>
          IO.unit
  
    }
  }

  private def dispatchCmd(globalFlags:GlobalFlags,cmd:CmdOptions,route:Route) :IO[ExitCode] = {
    cmd match {
        case c:ShowCmdOptions =>
            route.genTable(c).map {
                case Right(table) => 
                    table.prityPrint()
                    ExitCode.Success
                case Left(err) =>
                    println(err.msg)
                    ExitCode.Error

            }
        case c:VersionCmdOptions =>
            IO(println("x.x.x")) *> IO(ExitCode.Success)

    }
  }

}
