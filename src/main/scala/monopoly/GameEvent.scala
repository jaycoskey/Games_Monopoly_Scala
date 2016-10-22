package monopoly

import scala.Function1

class GameEvent { /* TODO */ }

// ====================
// Buildings
// ====================
/** Determine if house auction is indicated */
case class HouseAvailableCountIncreaseEvent() extends GameEvent {
  // TODO: new house count
}

/** Determine if hotel auction is indicated */
case class HotelAvailableCountIncreaseEvent() extends GameEvent {
  // TODO: new hotel count
}

// ====================
// Property
// ====================
/** Share information about players with other players */
case class AddPlayerEvent() extends GameEvent {
  // TODO: roster, new player name
  }
trait AddPlayerTrait {
  type AddPlayerAction = AddPlayerEvent => Unit
}

/** Causes rollback of conditional contracts */
case class PlayerBankruptcyEvent() extends GameEvent {
  // TODO: player, access to list of conditional offers
  }
trait PlayerBankruptcyTrait {
  type PlayerBankruptcyAction = PlayerBankruptcyEvent => Unit
}
case class PlayerLacksCashOnHandToPayDebtEvent() extends GameEvent {
 // TODO: player, cash on hand, amount of debt
 }

/** Next roll can proceed */
case class PlayerMoveEndsEvent() extends GameEvent {
  // TODO: player
  // TODO: rolled doubles? (but not on third jail roll?)
  }
trait PlayerMoveEndsTrait {
  type PlayerMoveEndsAction = PlayerMoveEndsEvent => Unit
}

/** Determine whether the player needs to raise funds */
case class PlayerIncursDebtEvent() extends GameEvent {
  // TODO player
  }

/** Determine if funds need to be diverted to Free Parking */
case class PlayerPaysBankEvent() extends GameEvent {
  }

/** Call method that executes turn, w/ one or more dice rolls. */
case class PlayerStartsTurnEvent() extends GameEvent {
  }
trait PlayerStartsTurnTrait {
  type PlayerStartsTurnAction = PlayerStartsTurnEvent => Unit
}

/** Move to next player */
case class PlayerEndsTurnEvent() extends GameEvent {
  }
trait PlayerEndsTurnTrait {
  type PlayerEndsTurnAction = PlayerEndsTurnEvent => Unit
}

// ====================
// Game Start/Stop/Winning/Losing
// ====================
/** Log incident, then fire PlayerLosesEvent */
case class GameStartEvent() extends GameEvent {
}
trait GameStartTrait {
  type GameStartAction = GameStartEvent => Unit
}

/** Log incident, then fire PlayerLosesEvent */
case class PlayerResignsEvent() extends GameEvent {
  }
  
/** Remove player from Roster */
case class PlayerLosesEvent() extends GameEvent {
  }

/** TODO: Determine if players wish to play again? */
case class PlayerWinsEvent() extends GameEvent {
  }

// ====================
// Contracts
// ====================
/** Offer has some parts unspecified */
case class PlayerMakesPartialOfferEvent() extends GameEvent {
  }

/** isConditional attribute---Offer is complete, but not yet accepted */
case class PlayerMakesOfferEvent() extends GameEvent {
}

/** isConditional attribute---Offer might still be contingent on debt being paid */
case class PlayerAcceptsOfferEvent() extends GameEvent {
}

/** Offer cancelled before acceptance */
case class PlayerCancelsOfferEvent() extends GameEvent {
}

/** Necessary, given PlayerBankruptcyEvent? */
case class RollBackConditionalOfferEvent() extends GameEvent {
}

// ====================
// Property
// ====================
/** Change isMortgaged status */
case class PropertyMortgagedEvent() extends GameEvent {
}

/** Check isMortgaged.  Give receiver the option of unmortgaging now or later. */
case class PropertyTransferredEvent() extends GameEvent {
}

/** Change isMortgaged status */
case class PropertyUnmortgagedEvent() extends GameEvent {
}

// ====================
// Token Movement
// ====================
/** Used for the Go BoardLocation */
case class TokenEntersBoardLocationEvent() extends GameEvent
trait TokenEntersBoardLocationTrait {
  type TokenEntersBoardLocationAction = TokenEntersBoardLocationEvent => Unit
}
// case class TokenExitsBoardLocation extends GameEvent {}

/** Dispatch by board location */
case class TokenLandsOnBoardLocationEvent() extends GameEvent
trait TokenLandsOnBoardLocationTrait {
  type TokenLandsOnBoardLocationAction = TokenLandsOnBoardLocationEvent => Unit
}
/** TODO */
case class TokenLandsOnPropertyEvent() extends GameEvent {
}
trait TokenLandsOnPropertyTrait {
  type TokenLandsOnPropertyAction = TokenLandsOnPropertyEvent => Unit
}
