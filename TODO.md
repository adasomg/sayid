- bottom line hidden in output

nrepl/sayid.el
x button to pretty print
x button to cider-inspect
x trying to trace unloaded ns problems???
x trace by dir
x trace by ns
x enable/disable traces
x back button (backspace)
x back button - save pos
x 'n' does inner trace from buf
~ better 'show trace' buffer
  x refresh maintains ns status
  x per ns
  x check whether each func is: outer, inner, enabled
  x trace commands
    x outer
    x inner
    x enable
    x disable
    x remove
    - the rest
  - enter-> jump to func
  x enter-> jump to ns detail view
  - colors

-  clj-mode bindings
  x sayid-trace-fn-enable
  x sayid-trace-fn-disable
  x sayid-inner-trace-fn
  x sayid-outer-trace-fn
  x sayid-show-traced-ns

- sayid-trace-mode bindings
  - sayid-traced-buf-enable-all
  - sayid-traced-buf-disable-all
  x remove ns  

- sourcerer
 - func that set traces
 - views
 

- what happens if you apply an inner trace twice?

x rename everything (inner/outer)

x have eval-last be more sane; check for existing traces
- use 'l' for backspace (says Tim?)

# BUGS
 x ANSI is SLOW
   x fix show-traces
   - could still be faster??
 x FIX PPRINT
 - doesn't inner-trace into `let` bindings
 - trace doesn't take sometimes -- eval last
 x multi-line let bind expr renders badly
 x 'p' in *sayid*
 x e/d to enable/disable ns from *traced*

 x query relative distance not working

? query by func maintains cursor on id

x color codes instead of ansi
- add meta so that let binds can be def'd and pprinted

x views

x sayid.el 0.0.2 on billpiel.com

- 'q' query from buffer

- in-emacs docs


- generate and insert interactive debug code
- refresh on apply view???
- def/pprint :selects
