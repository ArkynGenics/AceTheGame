/*
 * This Java source file was generated by the Gradle 'init' task.
 * template from https://docs.oracle.com/javase/8/javafx/get-started-tutorial/hello_world.htm
 */
package modder

import picocli.CommandLine

//
object App {
    fun cliInit(args: Array<String>) {
        val cli = CommandLine(ModderMainCmd())

        // ========== set some options =============
        // https://picocli.info/
        // allow case insensitive
        cli.setSubcommandsCaseInsensitive(true)
        cli.setOptionsCaseInsensitive(true)
        // allow abbreviations
        cli.setAbbreviatedOptionsAllowed(true)
        cli.setAbbreviatedSubcommandsAllowed(true)
        // execute
        val exitCode = cli.execute(*args)
        System.exit(exitCode)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Some testing
        if (Util.DoesCommandExist("adb")) {
            println("adb exist")
        } else {
            println("adb doesn't exist")
        }
        cliInit(args)
    }
}
