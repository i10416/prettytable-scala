package commands
import com.monovore.decline.{Command,Opts}
import cats.syntax.all._

sealed trait CmdOptions extends Product with Serializable

final case class ShowCmdOptions(fileName:String) extends CmdOptions

final case class VersionCmdOptions() extends CmdOptions

final case class GlobalFlags(noHeaders: Boolean)


object Commands {
  val flags: Opts[GlobalFlags] = {
    val noHeaderOpt = booleanFlag("no-headers",help="use the first line of csv file as header row.")
    noHeaderOpt.map(noHeader => GlobalFlags(noHeader))
  }
  
  val filePathName = Opts.argument[String]("FILEPARH")

  val show:Opts[CmdOptions] = Opts.argument[String]("FILENAME").map(ShowCmdOptions.apply)
  val version =
      Command(
        name = "version",
        header = "display version"
      )(Opts.unit.map(_=>VersionCmdOptions()))

  val commands:Opts[CmdOptions] = show.orElse(Opts.subcommand(version))

  val appCmd : Opts[(GlobalFlags,CmdOptions)] =  (flags,commands).tupled


   private def booleanFlag(long: String, help: String): Opts[Boolean] =
    Opts.flag(long = long, help = help).map(_ => true).withDefault(false)
}