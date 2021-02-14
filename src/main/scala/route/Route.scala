package route
import usecase.GenerateTableUsecase
import commands.ShowCmdOptions
import model.ITable
import os.Path
import cats.syntax.all._
import cats.effect.IO

sealed trait ShowCSVError extends Product with Serializable {
  def msg: String
}
final case class IsNotFile(path: Path) extends ShowCSVError {
  override def msg: String = s"User error: $path is not a file but scalaprettyprint expects is to be a file"
}
final case class ParseError() extends ShowCSVError {
  override def msg: String = s"Parse error:  failed to parse file"
}
class Route(generateTableUsecase:GenerateTableUsecase) {
  def genTable(cmd:ShowCmdOptions):IO[Either[ShowCSVError,ITable]] = {
    val path = os.pwd / cmd.fileName
    if os.isFile(path) then {
          generateTableUsecase.execute(path).pure[IO]
    } else {
      IsNotFile(path).asLeft.pure[IO]
    }
  }
}