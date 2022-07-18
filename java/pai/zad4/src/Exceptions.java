package zad4;

class TimeBankExceptions extends Exception {}
class UserNameTaken extends TimeBankExceptions{}
class WrongDateAndTimeExcpetion extends TimeBankExceptions{}
class NoSuchServiceException extends TimeBankExceptions{}
class NoServicesException extends TimeBankExceptions{}