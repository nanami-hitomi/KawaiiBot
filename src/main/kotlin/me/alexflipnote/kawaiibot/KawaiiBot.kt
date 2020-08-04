package me.alexflipnote.kawaiibot

import me.alexflipnote.kawaiibot.utils.CommandClasspathScanner
import me.alexflipnote.kawaiibot.utils.SpeedyBoi
import me.aurieh.ichigo.core.CommandHandler
import net.dv8tion.jda.bot.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.bot.sharding.ShardManager
import net.dv8tion.jda.core.entities.Game
import okhttp3.OkHttpClient
import org.slf4j.LoggerFactory
import java.awt.Color
import java.io.FileInputStream
import java.io.IOException
import java.util.*
import javax.security.auth.login.LoginException

object KawaiiBot {

    const val version = "3.2.1"
    private val bootTime = System.currentTimeMillis()
    //                          hitoccchi               rodg                myth
    val developerIds = setOf(173529942431236096L, 184038953920233472L, 133736400385212416L)
    var LOG = LoggerFactory.getLogger("KawaiiBot")

    val config = Properties()

    lateinit var shardManager: ShardManager
    lateinit var commandHandler: CommandHandler
    lateinit var httpClient: OkHttpClient
    lateinit var embedColor: Color

    var otherCommandUsage = 0
    var pornUsage = 0

    val uptime: Long
        get() = System.currentTimeMillis() - bootTime

    @Throws(LoginException::class, IOException::class)
    @JvmStatic
    fun main(args: Array<String>) {
        if (args.isNotEmpty() && args[0].equals("--sharded", ignoreCase = true)) {
            LOG.info("Running in production mode!")
            config.load(FileInputStream("config.properties"))
        } else {
            LOG.info("Running in development mode!")
            config.load(FileInputStream("dev.properties"))
        }

        val defaultPrefix = config.getProperty("prefix")
        embedColor = Color.decode(config.getProperty("color", "0xC29FAF"))

        val shardIdentifyDelay = config.getProperty("shardidentifydelay", "5000").toLong()

        httpClient = OkHttpClient.Builder().build()
        commandHandler = CommandHandler.Builder(defaultPrefix)
                .addCommandsAll(CommandClasspathScanner.scan(this::class.java.classLoader))
                .addDevelopersAll(developerIds)
                .build()

        shardManager = DefaultShardManagerBuilder()
                .setShardsTotal(-1)
                .setToken(config.getProperty("token"))
                .setGame(Game.playing(defaultPrefix + "help"))
                .addEventListeners(commandHandler)
                .setSessionController(SpeedyBoi(shardIdentifyDelay))
                .build()
    }
}
