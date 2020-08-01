package me.alexflipnote.kawaiibot.commands

import com.github.natanbc.weeb4j.image.NsfwFilter
import me.alexflipnote.kawaiibot.KawaiiBot
import me.alexflipnote.kawaiibot.extensions.clean
import me.alexflipnote.kawaiibot.utils.Helpers
import me.aurieh.ichigo.core.CommandContext
import me.aurieh.ichigo.core.ICommand
import me.aurieh.ichigo.core.annotations.Command
import net.dv8tion.jda.core.MessageBuilder
import net.dv8tion.jda.core.Permission

@Command(description = "High-five someone! o/\\o ", botPermissions = [Permission.MESSAGE_EMBED_LINKS, Permission.MESSAGE_ATTACH_FILES, Permission.MESSAGE_EXT_EMOJI], guildOnly = true)
class Highfive : ICommand {
    override fun run(ctx: CommandContext) {
        val m = ctx.args.asMember

        when {
            m == null ->
                ctx.send("Are you trying to high-five atoms...?")
            m.user.idLong == ctx.jda.selfUser.idLong ->
                ctx.send("*High-fives **${ctx.author.name}** back* <:highfive_L:415309531879571468><:highfive_R:415309533708288000>")
            m.user.idLong == ctx.author.idLong -> {
                val msg = MessageBuilder().setContent("*awkward...*").build()
                ctx.channel.sendFile(Helpers.getImageStream("images/selffive.gif"), "selffive.gif", msg).queue()
            }
            else -> {
                //TODO: Implement
                ctx.send("**${m.effectiveName.clean()}**, you got a high-five from **${ctx.author.name}**")
            }
        }
    }
}