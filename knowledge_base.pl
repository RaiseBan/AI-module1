% Heroes
hero(antimage).
hero(sniper).
hero(drow_ranger).
hero(pudge).
hero(lion).
hero(clinkz).
hero(slark).

% Roles
role(carry).
role(support).
role(tank).
role(initiator).

% Abilities
ability(blink).
ability(shrapnel).
ability(hook).
ability(finger_of_death).
ability(multishot).
ability(invisibility).
ability(pounce).

% Classes (Agility, Strength, Intelligence)
class(agility).
class(strength).
class(intelligence).

% Heroes and their roles
hero_role(antimage, carry).
hero_role(sniper, carry).
hero_role(drow_ranger, carry).
hero_role(pudge, tank).
hero_role(lion, support).
hero_role(clinkz, carry).
hero_role(slark, initiator).

% Heroes and their abilities
hero_ability(antimage, blink).
hero_ability(sniper, shrapnel).
hero_ability(pudge, hook).
hero_ability(lion, finger_of_death).
hero_ability(drow_ranger, multishot).
hero_ability(clinkz, invisibility).
hero_ability(slark, pounce).

% Heroes and their class (Agility, Strength, Intelligence)
hero_class(antimage, agility).
hero_class(sniper, agility).
hero_class(drow_ranger, agility).
hero_class(pudge, strength).
hero_class(lion, intelligence).
hero_class(clinkz, agility).
hero_class(slark, agility).

% Counter-picks (who counters whom)
counter_pick(lion, antimage).        % Antimage counters Lion
counter_pick(sniper, slark).         % Slark counters Sniper
counter_pick(drow_ranger, pudge).    % Pudge counters Drow Ranger
counter_pick(pudge, lion).           % Lion counters Pudge
counter_pick(drow_ranger, clinkz).   % Clinkz counters Drow Ranger
counter_pick(lion, slark).           % Slark counters Lion

% A hero is considered a carry if their role is carry
is_carry(X) :- hero_role(X, carry).

% A hero can use an ability if they have it
can_use_ability(X, A) :- hero_ability(X, A).

% A hero is considered an agility hero if their class is agility
is_agility(X) :- hero_class(X, agility).

% Two heroes are of the same class if their class matches
same_class(X, Y) :- hero_class(X, Class), hero_class(Y, Class), X \= Y.

% A hero is a counter-pick if they are strong against another hero
is_counter_pick(X, Y) :- counter_pick(Y, X).



Requests:

hero(antimage).
same_class(sniper, Who).
hero(Who), \+ is_counter_pick(Who, slark), Who \= slark.
can_use_ability(Who, invisibility).
is_agility(Who).
is_counter_pick(lion, Who).
is_carry(Who).
