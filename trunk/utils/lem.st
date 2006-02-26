/**
 * Name: lem
 * Description: Language for Executable Models
 * Author: James Ring <sjr@jdns.org>
 */

state lem extends HighlightEntry
{
  /* Comments. */
  /\/\*/ {
    comment_face (true);
    language_print ($0);
    call (c_comment);
    comment_face (false);
  }
  /\/\// {
    comment_face (true);
    language_print ($0);
    call (eat_one_line);
    comment_face (false);
  }

  /* String constants. */
  /\"/ {
    string_face (true);
    language_print ($0);
    call (c_string);
    string_face (false);
  }

  /* Keywords.
  */
  /\b(true|false|is|end|association|generalisation|between|bridge|class|or|domain|model|subsystem|type|description|state|transition|event|to|from|by|via|carries|creation|deletion|self|attribute|on|set|generate|delay|at|cancel|relate|across|creating|unrelate|select|one|any|many|instances|of|that|relates|related|where|create|instance|reclassify|delete|declare|for|each|in|while|if|else|elseif|specialisation|behaviour|identifier|primary|range|pattern|numeric|units|length|precision|refers|return|calculation|derived|referential|0..1|*|1..1|1..*|identifier)\b/ {
    keyword_face (true);
    language_print ($0);
    keyword_face (false);
  }


  /* Primitive types. */
  /arbitrary_id|boolean|numeric|integer|real|string|timestamp|unique_id|array/ {
    type_face (true);
    language_print ($0);
    type_face (false);
  }
}

/*
Local variables:
mode: c
End:
*/
