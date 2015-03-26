import datetime
from django.db import models
from django.utils import timezone


class Student(models.Model):
    def __str__(self):
        return self.first_name + " " + self.last_name
    first_name = models.CharField(max_length=200)
    last_name = models.CharField(max_length=200)


class Logins(models.Model):
    """Database to store login dates and times for student"""
    student = models.ForeignKey(Student)
    login_date = models.DateTimeField()


class Progress(models.Model):
    """Stores progress of a student in JCCC application process"""
    def __str__(self):
        return str(self.student) + " " + str(self.percentage) + "%"
    student = models.ForeignKey(Student)
    HighSchoolTranscript_received = models.BooleanField('High School Transcript Received', default=True)
    PersonalInformation_completed = models.BooleanField('Personal Information Completed', default=True)
    PreviousEducation_completed = models.BooleanField('Previous Education Completed', default=True)
    AcademicMajor_completed = models.BooleanField('Academic Major Completed', default=True)
    Citizenship_completed = models.BooleanField('Citizenship Completed', default=True)
    Address_completed = models.BooleanField('Address Information Completed', default=True)
    Application_submitted = models.BooleanField('Application Submitted', default=True)

    @property
    def percentage(self):
        return ((int(self.HighSchoolTranscript_received) +
                int(self.PersonalInformation_completed) +
                int(self.PreviousEducation_completed) +
                int(self.AcademicMajor_completed) +
                int(self.Citizenship_completed) +
                int(self.Address_completed) +
                int(self.Application_submitted)) / 7.0 * 100)
