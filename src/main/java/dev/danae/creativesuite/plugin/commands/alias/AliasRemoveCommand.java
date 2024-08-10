package dev.danae.creativesuite.plugin.commands.alias;

import dev.danae.creativesuite.plugin.commands.PluginComponentCommand;
import dev.danae.creativesuite.plugin.components.commands.AliasComponent;
import dev.danae.creativesuite.util.parser.ParserException;
import dev.danae.creativesuite.util.commands.CommandContext;
import dev.danae.creativesuite.util.commands.CommandException;
import dev.danae.creativesuite.util.commands.CommandUsageException;
import java.util.List;


public class AliasRemoveCommand extends PluginComponentCommand<AliasComponent>
{
  // Constructor
  public AliasRemoveCommand(AliasComponent component)
  {
    super(component, "creativesuite.alias.remove");
  }
    
  
  // Handle the command
  @Override
  public void handle(CommandContext context) throws CommandException, CommandUsageException
  {
    try
    {
      // Validate the number of arguments
      if (!context.hasArgumentsCount(1))
        throw new CommandUsageException();
      
      // Create a scanner for the arguments
      var scanner = context.getArgumentsScanner();
      
      // Parse the arguments
      var key = scanner.nextNamespacedKey();
      var existingKey = this.getComponent().getAliases().containsKey(key);
      if (!existingKey)
        throw new CommandException(String.format("Alias %s does not exist", key.toString()));

      // Remove the alias
      this.getComponent().getAliases().remove(key);

      // Send a message about the removed alias
      context.sendMessage(this.getComponent().formatAliasRemovedMessage(key));
    }
    catch (ParserException ex)
    {
      throw new CommandException(ex.getMessage(), ex);
    }
  }
  
  // Handle tab completion of the command
  @Override
  public List<String> handleTabCompletion(CommandContext context)
  {
    if (context.hasArgumentsCount(1))
      return this.getComponent().handleAliasTabCompletion(context.getArgument(0));
    else
      return List.of();
  }
}
