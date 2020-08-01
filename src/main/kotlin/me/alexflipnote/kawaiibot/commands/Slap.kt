package me.alexflipnote.kawaiibot.commands

import me.alexflipnote.kawaiibot.KawaiiBot
import me.alexflipnote.kawaiibot.utils.Helpers
import me.aurieh.ichigo.core.CommandContext
import me.aurieh.ichigo.core.ICommand
import me.aurieh.ichigo.core.annotations.Command
import net.dv8tion.jda.core.Permission

@Command(description = "Slap someone! o//o", botPermissions = [Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES], guildOnly = true)
class Slap : ICommand {
    override fun run(ctx: CommandContext) {
        val m = ctx.args.asMember

        when {
            m == null ->
                ctx.send("Are you trying to slap a ghost...?")
            m.user.idLong == ctx.jda.selfUser.idLong ->
                ctx.send("**${ctx.author.name}** we can no longer be friends ;-;")
            m.user.idLong == ctx.author.idLong ->
                ctx.channel.sendFile(Helpers.getImageStream("images/butwhy.gif"), "butwhy.gif").queue()
            else -> {
                RequestUtil.get("https://api.nekos.dev/api/v3/images/sfw/gif/slap").thenAccept {
                    val res = it.json()?.getString("url")
                    ctx.send("**${m.user.name}**,you got a slap from **${ctx.author.name}**\n$res")
                }.thenException { ctx.send("No slaps!") }
            }
        }
    }
}
